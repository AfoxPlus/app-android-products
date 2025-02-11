package com.afoxplus.products.usecases

import com.afoxplus.products.entities.LandingProducts
import com.afoxplus.products.usecases.actions.FetchLandingProductsByCurrentRestaurant
import com.afoxplus.products.usecases.repositories.ProductRepository
import com.afoxplus.uikit.common.ResultState
import com.afoxplus.uikit.objects.vendor.VendorShared
import java.lang.Exception
import javax.inject.Inject

internal class FetchLandingProductsByCurrentRestaurantUseCase @Inject constructor(
    private val productRepository: ProductRepository,
    private val vendorShared: VendorShared
) : FetchLandingProductsByCurrentRestaurant {
    override suspend fun invoke(): ResultState<LandingProducts> {
        val result = vendorShared.fetch()?.let { vendor ->
            productRepository.fetchLandingProducts(restaurantCode = vendor.restaurantId)
        }
        return result?.let { ResultState.Success(it) } ?: ResultState.Error(Exception("Empty List"))
    }
}