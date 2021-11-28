package com.afoxplus.products.usecases.actions

import com.afoxplus.products.entities.Product

interface FetchAppetizer {
    suspend operator fun invoke(): List<Product>
}