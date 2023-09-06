package com.afoxplus.products.usecases.actions

import com.afoxplus.products.entities.Measure
import com.afoxplus.products.entities.Product
import com.afoxplus.uikit.result.ResultState

interface FindProduct {
    suspend operator fun invoke(code: String): ResultState<Product>
    suspend operator fun invoke(code: String, measure: Measure): ResultState<Product>
}