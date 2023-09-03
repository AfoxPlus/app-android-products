package com.afoxplus.products.repositories.sources.network.api

import com.afoxplus.network.annotations.ServiceClient
import com.afoxplus.network.api.NetworkResult
import com.afoxplus.network.api.UrlProvider
import com.afoxplus.products.repositories.sources.network.api.response.MeasureResponse
import retrofit2.Response
import retrofit2.http.GET

@ServiceClient(type = UrlProvider.Type.API_PRODUCTS)
internal interface MeasureApiNetwork {
    companion object {
        const val PATH_MEASURE: String = "measure"
    }

    @GET(PATH_MEASURE)
    suspend fun fetchMeasure(): NetworkResult<List<MeasureResponse>>
}