package com.afoxplus.products.repositories.sources.network.service

import com.afoxplus.network.extensions.map
import com.afoxplus.products.entities.Measure
import com.afoxplus.products.repositories.sources.network.MeasureNetworkDataSource
import com.afoxplus.products.repositories.sources.network.api.MeasureApiNetwork
import com.afoxplus.products.repositories.sources.network.api.response.MeasureResponse
import javax.inject.Inject

internal class MeasureNetworkService @Inject constructor(private val measureService: MeasureApiNetwork) :
    MeasureNetworkDataSource {

    override suspend fun fetchMeasure(): List<Measure> {
        val response = measureService.fetchMeasure()
        var list: List<Measure> = arrayListOf()
        response.map { list = MeasureResponse.mapToMeasure(it) }
        return list
    }

}