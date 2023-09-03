package com.afoxplus.products.usecases.actions

import com.afoxplus.products.entities.Product
import com.afoxplus.uikit.result.UIKitResultState

fun interface FetchProductByCurrentRestaurant {
    suspend operator fun invoke(productName: String): UIKitResultState<List<Product>>
}