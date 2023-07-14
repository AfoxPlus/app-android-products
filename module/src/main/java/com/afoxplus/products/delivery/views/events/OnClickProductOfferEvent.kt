package com.afoxplus.products.delivery.views.events

import com.afoxplus.products.entities.Product
import com.afoxplus.uikit.bus.UIKitEventBus

interface OnClickProductOfferEvent : UIKitEventBus {
    val product: Product

    private class BuildListener(override val product: Product) :
        OnClickProductOfferEvent

    companion object {
        fun build(product: Product): OnClickProductOfferEvent = BuildListener(product)
    }
}