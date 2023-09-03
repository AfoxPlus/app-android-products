package com.afoxplus.products.repositories.sources.network.service

import com.afoxplus.network.api.NetworkResult
import com.afoxplus.products.entities.Measure
import com.afoxplus.products.repositories.sources.network.MeasureNetworkDataSource
import com.afoxplus.products.repositories.sources.network.api.MeasureApiNetwork
import com.afoxplus.products.repositories.sources.network.api.response.MeasureResponse
import com.afoxplus.uikit.result.UIKitResultState
import javax.inject.Inject

internal class MeasureNetworkService @Inject constructor(private val measureService: MeasureApiNetwork) :
    MeasureNetworkDataSource {

    override suspend fun fetchMeasure(): UIKitResultState<List<Measure>> {
        return when (val response = measureService.fetchMeasure()) {
            is NetworkResult.Success -> {
                val list = response.data.map { MeasureResponse.mapToMeasure(it) }
                UIKitResultState.Success(list)
            }

            is NetworkResult.Error -> {
                UIKitResultState.Error(
                    response.message ?: API_PRODUCT_MEASURE_INTERNAL_ERROR,
                    response.code
                )
            }

            is NetworkResult.Exception -> {
                UIKitResultState.Error(API_PRODUCT_MEASURE_INTERNAL_ERROR)
            }
        }
    }

    companion object {
        private const val API_PRODUCT_MEASURE_INTERNAL_ERROR: String =
            "API Products Measure Internal Error"
    }
}