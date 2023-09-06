package com.afoxplus.products.repositories

import com.afoxplus.products.entities.Measure
import com.afoxplus.products.repositories.sources.network.MeasureNetworkDataSource
import com.afoxplus.products.usecases.repositories.MeasureRepository
import com.afoxplus.uikit.result.ResultState
import javax.inject.Inject

internal class MeasureRepositorySource @Inject constructor(
    private val measureNetworkDataSource: MeasureNetworkDataSource
) : MeasureRepository {
    override suspend fun fetch(): ResultState<List<Measure>> = measureNetworkDataSource.fetchMeasure()
}