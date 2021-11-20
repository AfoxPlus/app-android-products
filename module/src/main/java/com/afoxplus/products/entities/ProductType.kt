package com.afoxplus.products.entities

import android.os.Parcelable

interface ProductType : Parcelable {
    val code: String
    val name: String
}