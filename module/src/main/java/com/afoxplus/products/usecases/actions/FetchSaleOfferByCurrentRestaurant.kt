package com.afoxplus.products.usecases.actions

import com.afoxplus.products.entities.Product

fun interface FetchSaleOfferByCurrentRestaurant {
    suspend operator fun invoke(): List<Product>
}