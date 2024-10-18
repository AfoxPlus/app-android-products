package com.afoxplus.products.repositories.sources.network.api.response

import com.afoxplus.products.entities.EstablishmentSection
import com.afoxplus.products.entities.types.CardViewType
import com.google.gson.annotations.SerializedName

internal data class EstablishmentSectionResponse(
    @SerializedName("sectionName") var sectionName: String? = null,
    @SerializedName("sectionBackgroundToken") var sectionBackgroundToken: String? = null,
    @SerializedName("sectionColorToken") var sectionColorToken: String? = null,
    @SerializedName("productCardViewType") var productCardViewType: String? = null,
    @SerializedName("productGridColumnSize") var productGridColumnSize: Int? = null,
    @SerializedName("products") var products: List<ProductResponse>? = null,
) {
    companion object {
        private fun mapToEstablishmentSection(establishmentSectionResponse: EstablishmentSectionResponse): EstablishmentSection =
            EstablishmentSection(
                sectionName = establishmentSectionResponse.sectionName ?: "",
                sectionBackgroundToken = establishmentSectionResponse.sectionBackgroundToken ?: "",
                sectionColorToken = establishmentSectionResponse.sectionColorToken ?: "",
                productCardViewType = establishmentSectionResponse.productCardViewType?.let {
                    CardViewType.valueOf(
                        it
                    )
                } ?: CardViewType.HORIZONTAL,
                productGridColumnSize = establishmentSectionResponse.productGridColumnSize ?: 1,
                products = ProductResponse.mapToProduct(
                    establishmentSectionResponse.products ?: listOf()
                )
            )

        fun mapToEstablishmentSection(establishmentSectionListResponse: List<EstablishmentSectionResponse>): List<EstablishmentSection> =
            establishmentSectionListResponse.map { item -> mapToEstablishmentSection(item) }
    }
}