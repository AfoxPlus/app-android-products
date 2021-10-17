package com.afoxplus.products.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Measure(val code: String, val value: String) : Parcelable