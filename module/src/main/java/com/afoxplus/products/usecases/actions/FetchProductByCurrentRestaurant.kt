package com.afoxplus.products.usecases.actions

import com.afoxplus.products.entities.Product
import com.afoxplus.uikit.result.ResultState

fun interface FetchProductByCurrentRestaurant {
    suspend operator fun invoke(productName: String): ResultState<List<Product>>
}