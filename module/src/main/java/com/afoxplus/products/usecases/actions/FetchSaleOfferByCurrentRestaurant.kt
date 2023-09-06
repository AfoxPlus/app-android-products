package com.afoxplus.products.usecases.actions

import com.afoxplus.products.entities.Product
import com.afoxplus.uikit.result.ResultState

fun interface FetchSaleOfferByCurrentRestaurant {
    suspend operator fun invoke(): ResultState<List<Product>>
}