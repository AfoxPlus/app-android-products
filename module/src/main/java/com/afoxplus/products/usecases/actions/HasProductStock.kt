package com.afoxplus.products.usecases.actions

import com.afoxplus.products.entities.Product
import com.afoxplus.uikit.result.ResultState

fun interface HasProductStock {
    suspend operator fun invoke(product: Product, quantity: Int): ResultState<Boolean>
}