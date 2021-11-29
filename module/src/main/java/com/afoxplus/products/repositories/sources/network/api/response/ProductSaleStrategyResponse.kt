package com.afoxplus.products.repositories.sources.network.api.response

import com.afoxplus.products.entities.bussineslogic.SaleProductStrategy
import com.afoxplus.products.entities.bussineslogic.strategies.DiscountByOffer
import com.google.gson.annotations.SerializedName

internal data class ProductSaleStrategyResponse(
    @SerializedName("code") val strategyCode: String,
    @SerializedName("restaurant") val restaurant: RestaurantResponse,
    @SerializedName("parameters") val parameters: ProductSaleStrategyParametersResponse
) {
    companion object {
        fun mapToProductSaleStrategy(strategyResponse: ProductSaleStrategyResponse): SaleProductStrategy {
            return DiscountByOffer(
                strategyCode = strategyResponse.strategyCode,
                percentDiscount = strategyResponse.parameters.percentage ?: 0.0,
                restaurantCode = strategyResponse.restaurant.code,
                restaurantName = strategyResponse.restaurant.name
            )
        }
    }
}