package com.afoxplus.products.repositories.sources.network.api.response

import com.afoxplus.products.entities.ProductType
import com.afoxplus.products.entities.types.AppetizerDish
import com.afoxplus.products.entities.types.Generic
import com.afoxplus.products.entities.types.MenuDish
import com.google.gson.annotations.SerializedName

internal data class ProductTypeResponse(
    @SerializedName("code") val code: String,
    @SerializedName("name") val name: String
) {
    companion object {
        private const val MENU_PRODUCT_TYPE = "MENU"
        private const val APPETIZER_PRODUCT_TYPE = "APPETIZER"
        fun mapToProductType(response: ProductTypeResponse): ProductType {
            return when (response.code) {
                MENU_PRODUCT_TYPE -> MenuDish.build(code = response.code, name = response.name)
                APPETIZER_PRODUCT_TYPE -> AppetizerDish.build(
                    code = response.code, name = response.name
                )
                else -> getGenericProduct()
            }
        }

        fun getGenericProduct(): ProductType = Generic.build(code = "G001", name = "Generic")
    }
}