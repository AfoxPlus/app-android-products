package com.afoxplus.products.usecases

import com.afoxplus.products.entities.Product
import com.afoxplus.products.usecases.actions.FetchHomeOffer
import com.afoxplus.products.usecases.repositories.ProductRepository
import com.afoxplus.uikit.result.ResultState
import javax.inject.Inject

internal class FetchHomeOfferUseCase @Inject constructor(private val productRepository: ProductRepository) :
    FetchHomeOffer {
    override suspend fun invoke(): ResultState<List<Product>> = productRepository.fetchOffers()
}