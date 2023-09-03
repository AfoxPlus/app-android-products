package com.afoxplus.products.usecases.repositories

import com.afoxplus.products.entities.Measure
import com.afoxplus.uikit.result.UIKitResultState

internal interface MeasureRepository {
    suspend fun fetch(): UIKitResultState<List<Measure>>
}