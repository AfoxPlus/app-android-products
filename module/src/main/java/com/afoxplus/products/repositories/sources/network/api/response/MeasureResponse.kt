package com.afoxplus.products.repositories.sources.network.api.response

import com.afoxplus.products.entities.Measure
import com.google.gson.annotations.SerializedName

internal data class MeasureResponse(
    @SerializedName("code") val code: String,
    @SerializedName("value") val value: String
) {
    companion object {
        fun mapToMeasure(measureResponse: MeasureResponse): Measure = Measure(
            code = measureResponse.code,
            value = measureResponse.value
        )

        fun mapToMeasure(measureListResponse: List<MeasureResponse>): List<Measure> =
            measureListResponse.map { item -> mapToMeasure(item) }
    }
}