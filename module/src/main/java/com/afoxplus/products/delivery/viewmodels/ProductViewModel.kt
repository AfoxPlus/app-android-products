package com.afoxplus.products.delivery.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.afoxplus.products.delivery.models.ProductUIModel
import com.afoxplus.products.delivery.views.events.OnClickProductOfferEvent
import com.afoxplus.products.delivery.views.events.OnClickProductSaleEvent
import com.afoxplus.products.entities.Product
import com.afoxplus.products.usecases.actions.FetchProduct
import com.afoxplus.uikit.bus.EventBusListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ProductViewModel @Inject constructor(
    private val findProductsUseCase: FetchProduct,
    private val productEventBus: EventBusListener
) : ViewModel() {

    private val mProductSales: MutableLiveData<List<ProductUIModel>> by lazy { MutableLiveData<List<ProductUIModel>>() }
    val productSale: LiveData<List<ProductUIModel>> get() = mProductSales

    private val mProductOffer: MutableLiveData<List<ProductUIModel>> by lazy { MutableLiveData<List<ProductUIModel>>() }
    val productOffer: LiveData<List<ProductUIModel>> get() = mProductOffer

    fun fetchProductsRecommended() = viewModelScope.launch(Dispatchers.IO) {
        try {
            //TODO: Create use case for get recommended products
            val result = findProductsUseCase("pizza")
            mProductSales.postValue(result.map { item ->
                ProductUIModel(
                    ProductUIModel.VIEW_TYPE_PRODUCT_SALE,
                    item
                )
            })
            mProductOffer.postValue(result.map { item ->
                ProductUIModel(
                    ProductUIModel.VIEW_TYPE_PRODUCT_OFFER,
                    item
                )
            }.subList(0, 2))
        } catch (ex: Exception) {
            Log.d("PRODUCT", "${ex}")
        }
    }

    fun onClickProductEvent(product: Product) = viewModelScope.launch(Dispatchers.Main) {
        productEventBus.send(OnClickProductSaleEvent.build(product))
    }

    fun onClickProductOfferEvent(product: Product) = viewModelScope.launch(Dispatchers.Main) {
        productEventBus.send(OnClickProductOfferEvent.build(product))
    }
}