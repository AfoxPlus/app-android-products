package com.afoxplus.products.usecases

import com.afoxplus.products.entities.Product
import com.afoxplus.products.usecases.actions.FetchSaleOfferByCurrentRestaurant
import com.afoxplus.products.usecases.repositories.ProductRepository
import com.afoxplus.uikit.objects.vendor.VendorShared
import com.afoxplus.uikit.result.ResultState
import javax.inject.Inject

internal class FetchSaleOfferByCurrentRestaurantUseCase @Inject constructor(
    private val productRepository: ProductRepository,
    private val shared: VendorShared
) :
    FetchSaleOfferByCurrentRestaurant {
    override suspend fun invoke(): ResultState<List<Product>> {
        return shared.fetch()?.let { vendor ->
            productRepository.fetchSaleOffers(vendor.restaurantId)
        } ?: ResultState.Success(emptyList())
    }
}