package com.afoxplus.products.repositories.sources.network

import com.afoxplus.products.entities.Measure
import com.afoxplus.uikit.result.ResultState


internal interface MeasureNetworkDataSource {
    suspend fun fetchMeasure(): ResultState<List<Measure>>
}