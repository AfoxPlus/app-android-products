package com.afoxplus.products.usecases.actions

import com.afoxplus.products.entities.Product

interface FetchHomeOffer {
    suspend operator fun invoke(): List<Product>
}