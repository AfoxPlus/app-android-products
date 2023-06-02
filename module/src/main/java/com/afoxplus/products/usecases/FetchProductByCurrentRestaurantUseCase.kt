package com.afoxplus.products.usecases

import com.afoxplus.products.entities.Product
import com.afoxplus.products.usecases.actions.FetchProductByCurrentRestaurant
import com.afoxplus.products.usecases.repositories.ProductRepository
import com.afoxplus.uikit.objects.vendor.VendorShared
import javax.inject.Inject

internal class FetchProductByCurrentRestaurantUseCase @Inject constructor(
    private val productRepository: ProductRepository,
    private val vendorShared: VendorShared
) :
    FetchProductByCurrentRestaurant {
    override suspend fun invoke(productName: String): List<Product> {
        return vendorShared.fetch()?.let { vendor ->
            productRepository.fetch(vendor.restaurantId, productName)
        } ?: emptyList()
    }

}