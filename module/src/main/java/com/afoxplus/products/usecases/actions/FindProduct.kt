package com.afoxplus.products.usecases.actions

import com.afoxplus.products.entities.Measure
import com.afoxplus.products.entities.Product

interface FindProduct {
    suspend operator fun invoke(code: String): Product
    suspend operator fun invoke(code: String, measure: Measure): Product
}