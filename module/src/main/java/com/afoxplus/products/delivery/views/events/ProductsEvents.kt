package com.afoxplus.products.delivery.views.events

import com.afoxplus.products.entities.Product
import com.afoxplus.uikit.bus.UIKitEventBus

data class OnClickProductOfferEvent(val product: Product) : UIKitEventBus
data class OnClickProductSaleEvent(val product: Product) : UIKitEventBus