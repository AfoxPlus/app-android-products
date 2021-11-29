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
import com.afoxplus.products.usecases.actions.*
import com.afoxplus.uikit.bus.EventBusListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ProductViewModel @Inject constructor(
    private val fetchProducts: FetchProduct,
    private val fetchHomeOffer: FetchHomeOffer,
    private val fetchSaleOffer: FetchSaleOffer,
    private val fetchAppetizer: FetchAppetizer,
    private val fetchMenu: FetchMenu,
    private val productEventBus: EventBusListener
) : ViewModel() {

    private val mProductsSale: MutableLiveData<List<ProductUIModel>> by lazy { MutableLiveData<List<ProductUIModel>>() }
    val productsSale: LiveData<List<ProductUIModel>> get() = mProductsSale

    private val mProductOffer: MutableLiveData<List<ProductUIModel>> by lazy { MutableLiveData<List<ProductUIModel>>() }
    val productOffer: LiveData<List<ProductUIModel>> get() = mProductOffer

    private val mProductAppetizer: MutableLiveData<List<ProductUIModel>> by lazy { MutableLiveData<List<ProductUIModel>>() }
    val productAppetizer: LiveData<List<ProductUIModel>> get() = mProductAppetizer

    private val mProductsMenu: MutableLiveData<List<ProductUIModel>> by lazy { MutableLiveData<List<ProductUIModel>>() }
    val productMenu: LiveData<List<ProductUIModel>> get() = mProductsMenu

    private val mProductsHomeOffer: MutableLiveData<List<ProductUIModel>> by lazy { MutableLiveData<List<ProductUIModel>>() }
    val productsHomeOffer: LiveData<List<ProductUIModel>> get() = mProductsHomeOffer

    fun fetchProductSales() = viewModelScope.launch(Dispatchers.IO) {
        try {
            val result = fetchProducts("")
            mProductsSale.postValue(result.map { item ->
                ProductUIModel(
                    ProductUIModel.VIEW_TYPE_PRODUCT_SALE,
                    item
                )
            })
        } catch (ex: Exception) {
            Log.d("PRODUCT", "$ex")
        }
    }

    fun fetchProductOffers() = viewModelScope.launch(Dispatchers.IO) {
        try {
            val result = fetchSaleOffer()
            mProductOffer.postValue(result.map { item ->
                ProductUIModel(
                    ProductUIModel.VIEW_TYPE_PRODUCT_OFFER,
                    item
                )
            })
        } catch (ex: Exception) {
            Log.d("PRODUCT", "$ex")
        }
    }

    fun fetchProductsAppetizer() = viewModelScope.launch(Dispatchers.IO) {
        try {
            val result = fetchAppetizer()
            mProductAppetizer.postValue(result.map { item ->
                ProductUIModel(
                    ProductUIModel.VIEW_TYPE_PRODUCT_APPETIZER,
                    item
                )
            })
        } catch (ex: Exception) {
            Log.d("PRODUCT", "$ex")
        }
    }

    fun fetchProductsMenu() = viewModelScope.launch(Dispatchers.IO) {
        try {
            val result = fetchMenu()
            mProductsMenu.postValue(result.map { item ->
                ProductUIModel(
                    ProductUIModel.VIEW_TYPE_PRODUCT_MENU,
                    item
                )
            })
        } catch (ex: Exception) {
            Log.d("PRODUCT", "$ex")
        }
    }

    fun fetchProductsHomeOffer() = viewModelScope.launch(Dispatchers.IO) {
        try {
            val result = fetchHomeOffer()
            mProductsHomeOffer.postValue(result.map { item ->
                ProductUIModel(
                    ProductUIModel.VIEW_TYPE_PRODUCT_HOME_OFFER,
                    item
                )
            })
        } catch (ex: Exception) {
            Log.d("PRODUCT", "$ex")
        }
    }

    fun onClickProductEvent(product: Product) = viewModelScope.launch(Dispatchers.Main) {
        productEventBus.send(OnClickProductSaleEvent.build(product))
    }

    fun onClickProductOfferEvent(product: Product) = viewModelScope.launch(Dispatchers.Main) {
        productEventBus.send(OnClickProductOfferEvent.build(product))
    }
}