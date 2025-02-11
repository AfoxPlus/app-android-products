package com.afoxplus.products.usecases.actions

import com.afoxplus.products.entities.LandingProducts
import com.afoxplus.uikit.common.ResultState

interface FetchLandingProductsByCurrentRestaurant {
    suspend operator fun invoke(): ResultState<LandingProducts>
}