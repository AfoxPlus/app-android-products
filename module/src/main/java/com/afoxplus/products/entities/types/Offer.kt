package com.afoxplus.products.entities.types

import com.afoxplus.products.entities.ProductType
import kotlinx.parcelize.Parcelize

interface Offer : ProductType {
    @Parcelize
    private class OfferType(override val code: String, override val name: String) : Offer
    companion object {
        fun build(code: String, name: String): Offer = OfferType(code, name)
    }
}