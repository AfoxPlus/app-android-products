package com.afoxplus.products.usecases.actions

import com.afoxplus.products.entities.Product
import com.afoxplus.products.usecases.repositories.ProductRepository
import javax.inject.Inject

fun interface FetchProduct {
    suspend operator fun invoke(description: String): List<Product>
}