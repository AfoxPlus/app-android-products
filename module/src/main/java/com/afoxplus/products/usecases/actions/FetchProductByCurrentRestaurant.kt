package com.afoxplus.products.usecases.actions

import com.afoxplus.products.entities.Product

fun interface FetchProductByCurrentRestaurant {
    suspend operator fun invoke(productName: String): List<Product>
}