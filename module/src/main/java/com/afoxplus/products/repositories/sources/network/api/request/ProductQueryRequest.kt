package com.afoxplus.products.repositories.sources.network.api.request

import com.google.gson.annotations.SerializedName

internal data class ProductQueryRequest(@SerializedName("product_name") val productName: String)