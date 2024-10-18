package com.afoxplus.products.usecases

import com.afoxplus.products.entities.LandingProducts
import com.afoxplus.products.usecases.actions.FetchLandingProductsByCurrentRestaurant
import com.afoxplus.products.usecases.repositories.ProductRepository
import com.afoxplus.uikit.objects.vendor.VendorShared
import javax.inject.Inject

internal class FetchLandingProductsByCurrentRestaurantUseCase @Inject constructor(
    private val productRepository: ProductRepository,
    private val vendorShared: VendorShared
) : FetchLandingProductsByCurrentRestaurant {
    override suspend fun invoke(): LandingProducts? {
        return vendorShared.fetch()?.let { vendor ->
            productRepository.fetchLandingProducts(restaurantCode = vendor.restaurantId)
        }
    }
}