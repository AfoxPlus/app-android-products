package com.afoxplus.products.repositories.sources.network.api.response

import com.google.gson.annotations.SerializedName

data class ProductSaleStrategyParametersResponse(
    @SerializedName("percentage") val percentage: Double? = null,
    @SerializedName("marketName") val marketName: String? = null
)