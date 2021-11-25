package com.afoxplus.products.entities.types

import com.afoxplus.products.entities.ProductType
import kotlinx.parcelize.Parcelize

interface Generic : ProductType {
    @Parcelize
    private class GenericType(override val code: String, override val name: String) : Generic
    companion object {
        fun build(code: String, name: String): Generic = GenericType(code, name)
    }
}