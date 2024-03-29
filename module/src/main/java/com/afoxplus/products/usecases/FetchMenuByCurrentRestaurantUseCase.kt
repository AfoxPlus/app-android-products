package com.afoxplus.products.usecases

import com.afoxplus.products.entities.Product
import com.afoxplus.products.usecases.actions.FetchMenuByCurrentRestaurant
import com.afoxplus.products.usecases.repositories.ProductRepository
import com.afoxplus.uikit.objects.vendor.VendorShared
import javax.inject.Inject

internal class FetchMenuByCurrentRestaurantUseCase @Inject constructor(
    private val productRepository: ProductRepository,
    private val vendorShared: VendorShared
) :
    FetchMenuByCurrentRestaurant {
    override suspend fun invoke(): List<Product> {
        return vendorShared.fetch()?.let { vendor ->
            productRepository.fetchMenu(restaurantCode = vendor.restaurantId)
        } ?: emptyList()
    }
}