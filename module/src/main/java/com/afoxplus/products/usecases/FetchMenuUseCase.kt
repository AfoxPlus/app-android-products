package com.afoxplus.products.usecases

import com.afoxplus.products.entities.Product
import com.afoxplus.products.usecases.actions.FetchMenu
import com.afoxplus.products.usecases.repositories.ProductRepository
import javax.inject.Inject

internal class FetchMenuUseCase @Inject constructor(private val productRepository: ProductRepository) :
    FetchMenu {
    override suspend fun invoke(): List<Product> = productRepository.fetchMenu()
}