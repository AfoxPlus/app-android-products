package com.afoxplus.products.entities.types

import com.afoxplus.products.entities.MenuDish
import kotlinx.parcelize.Parcelize

@Parcelize
internal class MenuDishType(override val code: String, override val name: String) : MenuDish