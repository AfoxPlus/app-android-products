package com.afoxplus.products.usecases.actions

import com.afoxplus.products.entities.Measure
import com.afoxplus.products.entities.Product
import com.afoxplus.uikit.result.UIKitResultState

interface FindProduct {
    suspend operator fun invoke(code: String): UIKitResultState<Product>
    suspend operator fun invoke(code: String, measure: Measure): UIKitResultState<Product>
}