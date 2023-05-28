package com.afoxplus.products.demo.delivery.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.afoxplus.products.delivery.views.events.OnClickProductOfferEvent
import com.afoxplus.products.delivery.views.events.OnClickProductSaleEvent
import com.afoxplus.products.entities.Product
import com.afoxplus.uikit.bus.UIKitEvent
import com.afoxplus.uikit.bus.UIKitEventBusWrapper
import com.afoxplus.uikit.di.UIKitCoroutineDispatcher
import com.afoxplus.uikit.objects.vendor.Vendor
import com.afoxplus.uikit.objects.vendor.VendorShared
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val productEventBus: UIKitEventBusWrapper,
    private val coroutines: UIKitCoroutineDispatcher,
    private val vendorShared: VendorShared
) : ViewModel() {


    private val mProductClicked: MutableLiveData<UIKitEvent<Product>> by lazy { MutableLiveData<UIKitEvent<Product>>() }
    val productClicked: LiveData<UIKitEvent<Product>> get() = mProductClicked

    private val mProductOfferClicked: MutableLiveData<UIKitEvent<Product>> by lazy { MutableLiveData<UIKitEvent<Product>>() }
    val productOfferClicked: LiveData<UIKitEvent<Product>> get() = mProductOfferClicked

    init {

        vendorShared.save(Vendor(tableId = "01", restaurantId = "61a19c440b6de1476436de4a"))

        viewModelScope.launch(coroutines.getMainDispatcher()) {
            productEventBus.getBusEventFlow().collectLatest { event ->
                if (event is OnClickProductSaleEvent) {
                    mProductClicked.postValue(UIKitEvent(event.product))
                }
            }
        }

        viewModelScope.launch(coroutines.getMainDispatcher()) {
            productEventBus.getBusEventFlow().collectLatest { event ->
                if (event is OnClickProductOfferEvent) {
                    mProductOfferClicked.postValue(UIKitEvent(event.product))
                }
            }
        }
    }
}