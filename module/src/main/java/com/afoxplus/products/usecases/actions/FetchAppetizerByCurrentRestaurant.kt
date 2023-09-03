package com.afoxplus.products.usecases.actions

import com.afoxplus.products.entities.Product
import com.afoxplus.uikit.result.UIKitResultState

fun interface FetchAppetizerByCurrentRestaurant {
    suspend operator fun invoke(): UIKitResultState<List<Product>>
}