package com.afoxplus.products.repositories.sources.network.api.request

import com.google.gson.annotations.SerializedName

internal data class ProductFilterRequest(@SerializedName("input_search") val inputSearch: String)