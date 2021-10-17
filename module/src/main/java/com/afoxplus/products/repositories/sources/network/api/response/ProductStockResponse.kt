package com.afoxplus.products.repositories.sources.network.api.response

import com.google.gson.annotations.SerializedName

data class ProductStockResponse(@SerializedName("hasStock") val hasStock: Boolean)