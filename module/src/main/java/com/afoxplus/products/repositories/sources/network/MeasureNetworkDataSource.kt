package com.afoxplus.products.repositories.sources.network

import com.afoxplus.products.entities.Measure


internal interface MeasureNetworkDataSource {
    suspend fun fetchMeasure(): List<Measure>
}