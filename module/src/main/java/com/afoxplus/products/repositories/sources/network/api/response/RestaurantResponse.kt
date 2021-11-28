package com.afoxplus.products.repositories.sources.network.api.response

import com.google.gson.annotations.SerializedName

internal data class RestaurantResponse(
    @SerializedName("code") val code: String,
    @SerializedName("name") val name: String,
)