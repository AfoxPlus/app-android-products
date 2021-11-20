package com.afoxplus.products.repositories.sources.network.api.response

import com.afoxplus.products.entities.ProductType
import com.afoxplus.products.entities.types.GenericType
import com.afoxplus.products.entities.types.MenuDishType
import com.google.gson.annotations.SerializedName

internal data class ProductTypeResponse(
    @SerializedName("code") val code: String,
    @SerializedName("name") val name: String
) {
    companion object {
        private const val MENU_PRODUCT_TYPE = "MENU"
        fun mapToProductType(response: ProductTypeResponse): ProductType {
            return when (response.code) {
                MENU_PRODUCT_TYPE -> MenuDishType(code = response.code, name = response.name)
                else -> getGenericProduct()
            }
        }

        fun getGenericProduct(): ProductType = GenericType("G001", "Generic")
    }
}