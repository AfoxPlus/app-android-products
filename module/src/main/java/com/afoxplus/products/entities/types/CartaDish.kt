package com.afoxplus.products.entities.types

import com.afoxplus.products.entities.ProductType
import kotlinx.parcelize.Parcelize

interface CartaDish : ProductType {
    @Parcelize
    private class CartaDishType(override val code: String, override val name: String) : CartaDish
    companion object {
        fun build(code: String, name: String): CartaDish = CartaDishType(code, name)
    }
}