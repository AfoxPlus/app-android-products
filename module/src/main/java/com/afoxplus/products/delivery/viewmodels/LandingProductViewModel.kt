package com.afoxplus.products.delivery.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.afoxplus.products.delivery.views.events.OnClickProductSaleEvent
import com.afoxplus.products.entities.EstablishmentSection
import com.afoxplus.products.entities.Product
import com.afoxplus.products.usecases.actions.FetchLandingProductsByCurrentRestaurant
import com.afoxplus.uikit.bus.UIKitEventBusWrapper
import com.afoxplus.uikit.common.ResultState
import com.afoxplus.uikit.common.UIState
import com.afoxplus.uikit.di.UIKitCoroutineDispatcher
import com.afoxplus.uikit.exceptions.UIException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class LandingProductViewModel @Inject constructor(
    private val fetchLandingProductsByCurrentRestaurant: FetchLandingProductsByCurrentRestaurant,
    private val uiKitCoroutineDispatcher: UIKitCoroutineDispatcher,
    private val eventBusWrapper: UIKitEventBusWrapper
) : ViewModel() {
    private val _productSections: MutableStateFlow<UIState<List<EstablishmentSection>>> by lazy {
        MutableStateFlow(UIState.OnLoading())
    }
    val productSections = _productSections.asStateFlow()

    init {
        loadProducts()
    }

    private fun loadProducts() {
        viewModelScope.launch(uiKitCoroutineDispatcher.getIODispatcher()) {
            when (val productData = fetchLandingProductsByCurrentRestaurant()) {
                is ResultState.Error -> _productSections.value =
                    UIState.OnError(UIException(message = productData.exception.message))

                is ResultState.Success -> _productSections.value =
                    UIState.OnSuccess(productData.data.establishmentSection)
            }
        }
    }

    fun onProductClicked(product: Product) {
        viewModelScope.launch(uiKitCoroutineDispatcher.getMainDispatcher()) {
            eventBusWrapper.send(OnClickProductSaleEvent(product))
        }
    }
}
