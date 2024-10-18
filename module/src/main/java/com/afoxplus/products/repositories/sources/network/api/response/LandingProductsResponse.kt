package com.afoxplus.products.repositories.sources.network.api.response

import com.afoxplus.products.entities.LandingProducts
import com.google.gson.annotations.SerializedName

internal data class LandingProductsResponse(
    @SerializedName("establishment") var establishment: String ? = null,
    @SerializedName("establishmentType") var establishmentType: String? = null,
    @SerializedName("establishmentSection") var establishmentSection: List<EstablishmentSectionResponse>? = null,
) {
    companion object {
        fun mapToLandingProduct(landingProductsResponse: LandingProductsResponse): LandingProducts =
            LandingProducts(
                establishment = landingProductsResponse.establishment ?: "",
                establishmentType = landingProductsResponse.establishmentType ?: "",
                establishmentSection = EstablishmentSectionResponse.mapToEstablishmentSection(
                    landingProductsResponse.establishmentSection ?: listOf()
                )
            )
    }
}
