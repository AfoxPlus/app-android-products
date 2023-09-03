package com.afoxplus.products.repositories.sources.network

import com.afoxplus.products.entities.Measure
import com.afoxplus.uikit.result.UIKitResultState


internal interface MeasureNetworkDataSource {
    suspend fun fetchMeasure(): UIKitResultState<List<Measure>>
}