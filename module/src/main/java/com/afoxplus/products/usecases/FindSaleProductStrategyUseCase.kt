package com.afoxplus.products.usecases

import com.afoxplus.products.entities.Product
import com.afoxplus.products.entities.bussineslogic.SaleProductStrategy
import com.afoxplus.products.usecases.actions.FindSaleProductStrategy
import com.afoxplus.products.usecases.repositories.ProductRepository
import com.afoxplus.uikit.result.UIKitResultState
import javax.inject.Inject

internal class FindSaleProductStrategyUseCase @Inject constructor(private val productRepository: ProductRepository) :
    FindSaleProductStrategy {
    override suspend fun invoke(product: Product): UIKitResultState<SaleProductStrategy> {
        TODO("Not yet implemented")
    }
}