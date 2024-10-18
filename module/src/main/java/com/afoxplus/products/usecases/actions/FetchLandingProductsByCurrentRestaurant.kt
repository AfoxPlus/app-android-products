package com.afoxplus.products.usecases.actions

import com.afoxplus.products.entities.LandingProducts

interface FetchLandingProductsByCurrentRestaurant {
    suspend operator fun invoke(): LandingProducts?
}