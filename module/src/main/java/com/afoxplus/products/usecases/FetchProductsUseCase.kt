package com.afoxplus.products.usecases

import com.afoxplus.products.entities.Product
import com.afoxplus.products.usecases.actions.FetchProduct
import com.afoxplus.products.usecases.repositories.ProductRepository
import javax.inject.Inject

internal class FetchProductsUseCase @Inject constructor(private val productRepository: ProductRepository) :
    FetchProduct {
    override suspend fun invoke(description: String): List<Product> =
        productRepository.fetch(description)
}