package com.afoxplus.products.usecases.actions

import com.afoxplus.products.entities.Measure

fun interface FetchMeasure {
    suspend operator fun invoke(): List<Measure>
}