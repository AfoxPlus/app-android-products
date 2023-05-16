package com.afoxplus.products.delivery.views.events

import com.afoxplus.products.entities.Product
import com.afoxplus.uikit.bus.EventBus

interface OnClickProductSaleEvent : EventBus {
    val product: Product

    private class BuildListener(override val product: Product) :
        OnClickProductSaleEvent

    companion object {
        fun build(product: Product): OnClickProductSaleEvent = BuildListener(product)
    }
}