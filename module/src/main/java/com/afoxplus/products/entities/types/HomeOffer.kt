package com.afoxplus.products.entities.types

import com.afoxplus.products.entities.ProductType
import kotlinx.parcelize.Parcelize

interface HomeOffer : ProductType {
    @Parcelize
    private class HomeOfferType(override val code: String, override val name: String) : HomeOffer
    companion object {
        fun build(code: String, name: String): HomeOffer = HomeOfferType(code, name)
    }
}