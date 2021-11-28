package com.afoxplus.products.usecases

import com.afoxplus.products.entities.Product
import com.afoxplus.products.usecases.actions.FetchAppetizer
import com.afoxplus.products.usecases.repositories.ProductRepository
import javax.inject.Inject

internal class FetchAppetizerUseCase @Inject constructor(private val productRepository: ProductRepository) :
    FetchAppetizer {
    override suspend fun invoke(): List<Product> = productRepository.fetchAppetizers()
}