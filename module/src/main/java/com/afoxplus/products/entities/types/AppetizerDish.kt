package com.afoxplus.products.entities.types

import kotlinx.parcelize.Parcelize

interface AppetizerDish : MenuDish {

    @Parcelize
    private class AppetizerDishType(override val code: String, override val name: String) :
        AppetizerDish

    companion object {
        fun build(code: String, name: String): AppetizerDish = AppetizerDishType(code, name)
    }
}