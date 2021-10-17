package com.afoxplus.products.usecases.repositories

import com.afoxplus.products.entities.Measure

internal interface MeasureRepository {
    suspend fun fetch(): List<Measure>
}