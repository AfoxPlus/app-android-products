package com.afoxplus.products.delivery.views.events

import com.afoxplus.products.entities.Product
import com.afoxplus.uikit.bus.EventBus

interface OnClickItemRecommendedEvent : EventBus {
    val product: Product

    private class BuildListener(override val product: Product) :
        OnClickItemRecommendedEvent

    companion object {
        fun build(product: Product): OnClickItemRecommendedEvent = BuildListener(product)
    }
}