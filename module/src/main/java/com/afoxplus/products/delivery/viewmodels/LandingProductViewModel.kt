package com.afoxplus.products.delivery.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.afoxplus.products.delivery.views.events.OnClickProductSaleEvent
import com.afoxplus.products.entities.EstablishmentSection
import com.afoxplus.products.entities.Product
import com.afoxplus.products.usecases.actions.FetchLandingProductsByCurrentRestaurant
import com.afoxplus.uikit.bus.UIKitEventBusWrapper
import com.afoxplus.uikit.di.UIKitCoroutineDispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class LandingProductViewModel @Inject constructor(
    private val fetchLandingProductsByCurrentRestaurant: FetchLandingProductsByCurrentRestaurant,
    private val uiKitCoroutineDispatcher: UIKitCoroutineDispatcher,
    private val eventBusWrapper: UIKitEventBusWrapper
) : ViewModel() {
    private val _productSections = MutableStateFlow<List<EstablishmentSection>>(emptyList())
    val productSections: StateFlow<List<EstablishmentSection>> = _productSections

    init {
        loadProducts()
    }

    private fun loadProducts() {
        viewModelScope.launch(uiKitCoroutineDispatcher.getIODispatcher()) {
            val productData = fetchLandingProductsByCurrentRestaurant.invoke()
            _productSections.value = productData?.establishmentSection ?: listOf()
        }
    }

    fun onProductClicked(product: Product) {
        viewModelScope.launch(uiKitCoroutineDispatcher.getMainDispatcher()) {
            eventBusWrapper.send(OnClickProductSaleEvent(product))
        }
    }
}
