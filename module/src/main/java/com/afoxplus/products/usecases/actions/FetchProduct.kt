package com.afoxplus.products.usecases.actions

import com.afoxplus.products.entities.Product

fun interface FetchProduct {
    suspend operator fun invoke(description: String): List<Product>
}