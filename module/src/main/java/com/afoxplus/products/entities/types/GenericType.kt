package com.afoxplus.products.entities.types

import com.afoxplus.products.entities.Generic
import kotlinx.parcelize.Parcelize

@Parcelize
internal class GenericType(override val code: String, override val name: String) : Generic