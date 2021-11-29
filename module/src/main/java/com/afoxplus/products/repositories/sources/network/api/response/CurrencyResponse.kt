package com.afoxplus.products.repositories.sources.network.api.response

import com.afoxplus.products.entities.Currency
import com.google.gson.annotations.SerializedName

internal data class CurrencyResponse(
    @SerializedName("code") val code: String,
    @SerializedName("value") val value: String
) {
    companion object {
        fun mapToCurrency(measureResponse: CurrencyResponse): Currency = Currency(
            code = measureResponse.code,
            value = measureResponse.value
        )

        fun mapToMCurrency(currencyListResponse: List<CurrencyResponse>): List<Currency> =
            currencyListResponse.map { item -> mapToCurrency(item) }
    }
}
