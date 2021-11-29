package com.afoxplus.products.repositories.sources.network.api.response

import com.afoxplus.products.entities.ProductType
import com.afoxplus.products.entities.types.*
import com.google.gson.annotations.SerializedName

internal data class ProductTypeResponse(
    @SerializedName("code") val code: String,
    @SerializedName("name") val name: String
) {
    companion object {
        private const val PRODUCT_MENU_TYPE = "PRODUCT_MENU"
        private const val PRODUCT_APPETIZER_TYPE = "PRODUCT_APPETIZER"
        private const val PRODUCT_HOME_OFFER_TYPE = "PRODUCT_HOME_OFFER"
        private const val PRODUCT_SALE_TYPE = "PRODUCT_SALE"
        private const val PRODUCT_SALE_OFFER_TYPE = "PRODUCT_OFFER"

        fun mapToProductType(response: ProductTypeResponse): ProductType {
            return when (response.code) {
                PRODUCT_MENU_TYPE -> MenuDish.build(code = response.code, name = response.name)
                PRODUCT_APPETIZER_TYPE -> AppetizerDish.build(
                    code = response.code,
                    name = response.name
                )
                PRODUCT_HOME_OFFER_TYPE -> HomeOffer.build(
                    code = response.code,
                    name = response.name
                )
                PRODUCT_SALE_TYPE -> CartaDish.build(code = response.code, name = response.name)
                PRODUCT_SALE_OFFER_TYPE -> Offer.build(code = response.code, name = response.name)
                else -> getGenericProduct()
            }
        }

        fun getGenericProduct(): ProductType =
            CartaDish.build(code = "PRODUCT_SALE", name = "Platos a la carta")
    }
}