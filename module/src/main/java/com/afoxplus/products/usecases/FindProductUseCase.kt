package com.afoxplus.products.usecases

import com.afoxplus.products.entities.Measure
import com.afoxplus.products.entities.Product
import com.afoxplus.products.usecases.actions.FindProduct
import com.afoxplus.products.usecases.repositories.ProductRepository
import javax.inject.Inject

internal class FindProductUseCase @Inject constructor(private val productRepository: ProductRepository) :
    FindProduct {
    override suspend fun invoke(code: String): Product = productRepository.find(code)

    override suspend fun invoke(code: String, measure: Measure): Product =
        productRepository.find(code, measure)
}