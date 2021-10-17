package com.afoxplus.products.delivery.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.afoxplus.products.delivery.views.events.OnClickItemRecommendedEvent
import com.afoxplus.products.entities.Product
import com.afoxplus.products.usecases.actions.FetchProduct
import com.afoxplus.uikit.bus.EventBusListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class RecommendedProductsViewModel @Inject constructor(
    private val findProductsUseCase: FetchProduct,
    private val productEventBus: EventBusListener
) : ViewModel() {

    private val mProducts: MutableLiveData<List<Product>> by lazy { MutableLiveData<List<Product>>() }
    val products: LiveData<List<Product>> get() = mProducts

    fun fetchProductsRecommended() = viewModelScope.launch(Dispatchers.IO) {
        try {
            //TODO: Create use case for get recommended products
            val result = findProductsUseCase("pizza")
            mProducts.postValue(result)
        } catch (ex: Exception) {
            Log.d("PRODUCT", "${ex}")
        }
    }

    fun onClickItemRecommendedProduct(product: Product) = viewModelScope.launch(Dispatchers.Main) {
        productEventBus.send(OnClickItemRecommendedEvent.build(product))
    }
}