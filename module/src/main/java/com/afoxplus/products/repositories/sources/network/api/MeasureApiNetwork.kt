package com.afoxplus.products.repositories.sources.network.api

import com.afoxplus.products.repositories.sources.network.api.response.MeasureResponse
import retrofit2.Response
import retrofit2.http.GET

internal interface MeasureApiNetwork {
    companion object {
        const val PATH_MEASURE: String = "measure"
    }

    @GET(PATH_MEASURE)
    suspend fun fetchMeasure(): Response<List<MeasureResponse>>
}