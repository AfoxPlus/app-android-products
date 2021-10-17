package com.afoxplus.products.usecases

import com.afoxplus.products.entities.Product
import com.afoxplus.products.usecases.actions.HasProductStock
import javax.inject.Inject


internal class HasProductStockUseCase @Inject constructor() : HasProductStock {
    override suspend fun invoke(product: Product, quantity: Int): Boolean = true
}