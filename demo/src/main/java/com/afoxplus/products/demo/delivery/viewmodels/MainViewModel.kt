package com.afoxplus.products.demo.delivery.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.afoxplus.products.delivery.views.events.OnClickProductOfferEvent
import com.afoxplus.products.delivery.views.events.OnClickProductSaleEvent
import com.afoxplus.products.entities.Product
import com.afoxplus.uikit.bus.Event
import com.afoxplus.uikit.bus.EventBusListener
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val productEventBus: EventBusListener
) : ViewModel() {


    private val mProductClicked: MutableLiveData<Event<Product>> by lazy { MutableLiveData<Event<Product>>() }
    val productClicked: LiveData<Event<Product>> get() = mProductClicked

    private val mProductOfferClicked: MutableLiveData<Event<Product>> by lazy { MutableLiveData<Event<Product>>() }
    val productOfferClicked: LiveData<Event<Product>> get() = mProductOfferClicked

    init {
        viewModelScope.launch(Dispatchers.Main) {
            productEventBus.subscribe().collectLatest { event ->
                if (event is OnClickProductSaleEvent) {
                    mProductClicked.postValue(Event(event.product))
                }
            }
        }

        viewModelScope.launch(Dispatchers.Main) {
            productEventBus.subscribe().collectLatest { event ->
                if (event is OnClickProductOfferEvent) {
                    mProductOfferClicked.postValue(Event(event.product))
                }
            }
        }
    }
}