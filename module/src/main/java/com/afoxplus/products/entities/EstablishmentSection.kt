package com.afoxplus.products.entities

import com.afoxplus.products.entities.types.CardViewType

data class EstablishmentSection(
    val sectionName: String,
    val sectionBackgroundToken: String,
    val sectionColorToken: String,
    val productCardViewType: CardViewType,
    val productGridColumnSize: Int,
    val products: List<Product>
)
