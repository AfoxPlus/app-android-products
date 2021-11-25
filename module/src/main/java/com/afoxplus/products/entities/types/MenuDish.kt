package com.afoxplus.products.entities.types

import com.afoxplus.products.entities.ProductType
import kotlinx.parcelize.Parcelize

interface MenuDish : ProductType {
    @Parcelize
    private class MenuDishType(override val code: String, override val name: String) : MenuDish
    companion object {
        fun build(code: String, name: String): MenuDish = MenuDishType(code, name)
    }
}
