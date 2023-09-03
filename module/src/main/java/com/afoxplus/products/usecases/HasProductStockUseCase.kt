package com.afoxplus.products.usecases

import com.afoxplus.products.entities.Product
import com.afoxplus.products.usecases.actions.HasProductStock
import com.afoxplus.uikit.result.UIKitResultState
import javax.inject.Inject


internal class HasProductStockUseCase @Inject constructor() : HasProductStock {
    override suspend fun invoke(product: Product, quantity: Int): UIKitResultState<Boolean> =
        UIKitResultState.Success(true)
}