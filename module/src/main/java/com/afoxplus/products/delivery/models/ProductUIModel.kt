package com.afoxplus.products.delivery.models

import com.afoxplus.products.entities.Product

internal data class ProductUIModel(val viewType: Int, val product: Product) {
    companion object {
        const val VIEW_TYPE_PRODUCT_SALE = 0
        const val VIEW_TYPE_PRODUCT_OFFER = 1
        const val VIEW_TYPE_PRODUCT_MENU = 2
        const val VIEW_TYPE_PRODUCT_APPETIZER = 3
    }
}