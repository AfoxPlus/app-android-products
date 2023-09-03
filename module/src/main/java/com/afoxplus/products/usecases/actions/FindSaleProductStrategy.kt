package com.afoxplus.products.usecases.actions

import com.afoxplus.products.entities.Product
import com.afoxplus.products.entities.bussineslogic.SaleProductStrategy
import com.afoxplus.uikit.result.UIKitResultState

fun interface FindSaleProductStrategy {
    suspend operator fun invoke(product: Product): UIKitResultState<SaleProductStrategy>
}