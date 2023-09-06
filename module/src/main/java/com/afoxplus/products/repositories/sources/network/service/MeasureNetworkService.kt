package com.afoxplus.products.repositories.sources.network.service

import com.afoxplus.network.api.NetworkResult
import com.afoxplus.products.entities.Measure
import com.afoxplus.products.repositories.sources.network.MeasureNetworkDataSource
import com.afoxplus.products.repositories.sources.network.api.MeasureApiNetwork
import com.afoxplus.products.repositories.sources.network.api.response.MeasureResponse
import com.afoxplus.uikit.result.ErrorMessage
import com.afoxplus.uikit.result.ErrorType
import com.afoxplus.uikit.result.ResultState
import javax.inject.Inject

internal class MeasureNetworkService @Inject constructor(private val measureService: MeasureApiNetwork) :
    MeasureNetworkDataSource {

    override suspend fun fetchMeasure(): ResultState<List<Measure>> {
        return when (val response = measureService.fetchMeasure()) {
            is NetworkResult.Success -> {
                val list = response.data.map { MeasureResponse.mapToMeasure(it) }
                ResultState.Success(list)
            }

            is NetworkResult.Error -> {
                ResultState.Error(
                    ErrorMessage(
                        code = response.code,
                        title = "",
                        message = response.message ?: API_PRODUCT_MEASURE_INTERNAL_ERROR,
                        errorType = ErrorType.ERROR
                    )
                )
            }

            is NetworkResult.Exception -> {
                ResultState.Error(
                    ErrorMessage(
                        title = "",
                        message = API_PRODUCT_MEASURE_INTERNAL_ERROR,
                        errorType = ErrorType.EXCEPTION
                    )
                )
            }
        }
    }

    companion object {
        private const val API_PRODUCT_MEASURE_INTERNAL_ERROR: String =
            "API Products Measure Internal Error"
    }
}