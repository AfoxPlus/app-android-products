package com.afoxplus.products.usecases.actions

import com.afoxplus.products.entities.Product
import com.afoxplus.products.entities.bussineslogic.SaleProductStrategy

fun interface FindSaleProductStrategy {
    suspend operator fun invoke(product: Product): SaleProductStrategy
}