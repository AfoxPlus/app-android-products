package com.afoxplus.products.entities

data class LandingProducts(
    val establishment: String,
    val establishmentType: String,
    val establishmentSection: List<EstablishmentSection>
)