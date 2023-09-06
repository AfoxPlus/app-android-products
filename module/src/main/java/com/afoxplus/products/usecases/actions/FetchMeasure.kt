package com.afoxplus.products.usecases.actions

import com.afoxplus.products.entities.Measure
import com.afoxplus.uikit.result.ResultState

fun interface FetchMeasure {
    suspend operator fun invoke(): ResultState<List<Measure>>
}