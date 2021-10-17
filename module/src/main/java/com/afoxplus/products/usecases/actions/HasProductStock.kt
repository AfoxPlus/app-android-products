package com.afoxplus.products.usecases.actions

import com.afoxplus.products.entities.Product

fun interface HasProductStock {
    suspend operator fun invoke(product: Product, quantity: Int): Boolean
}