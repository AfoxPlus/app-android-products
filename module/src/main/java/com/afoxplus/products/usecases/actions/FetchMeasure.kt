package com.afoxplus.products.usecases.actions

import com.afoxplus.products.entities.Measure
import com.afoxplus.uikit.result.UIKitResultState

fun interface FetchMeasure {
    suspend operator fun invoke(): UIKitResultState<List<Measure>>
}