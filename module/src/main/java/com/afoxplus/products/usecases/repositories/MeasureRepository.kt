package com.afoxplus.products.usecases.repositories

import com.afoxplus.products.entities.Measure
import com.afoxplus.uikit.result.ResultState

internal interface MeasureRepository {
    suspend fun fetch(): ResultState<List<Measure>>
}