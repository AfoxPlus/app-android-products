package com.afoxplus.products.usecases

import com.afoxplus.products.entities.Product
import com.afoxplus.products.usecases.actions.FetchAppetizerByCurrentRestaurant
import com.afoxplus.products.usecases.repositories.ProductRepository
import com.afoxplus.uikit.objects.vendor.VendorShared
import com.afoxplus.uikit.result.ResultState
import javax.inject.Inject

internal class FetchAppetizerByCurrentRestaurantUseCase @Inject constructor(
    private val productRepository: ProductRepository,
    private val vendorShared: VendorShared
) : FetchAppetizerByCurrentRestaurant {
    override suspend fun invoke(): ResultState<List<Product>> {
        return vendorShared.fetch()?.let { vendor ->
            productRepository.fetchAppetizers(restaurantCode = vendor.restaurantId)
        } ?: ResultState.Success(emptyList())
    }
}