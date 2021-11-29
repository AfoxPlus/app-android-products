package com.afoxplus.products.usecases

import com.afoxplus.products.entities.Product
import com.afoxplus.products.usecases.actions.FetchSaleOffer
import com.afoxplus.products.usecases.repositories.ProductRepository
import javax.inject.Inject

internal class FetchSaleOfferUseCase @Inject constructor(private val productRepository: ProductRepository) :
    FetchSaleOffer {
    override suspend fun invoke(): List<Product> = productRepository.fetchSaleOffers()
}