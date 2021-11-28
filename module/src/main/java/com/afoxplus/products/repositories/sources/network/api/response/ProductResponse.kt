package com.afoxplus.products.repositories.sources.network.api.response

import com.afoxplus.products.entities.Product
import com.google.gson.annotations.SerializedName

internal data class ProductResponse(
    @SerializedName("code") val code: String,
    @SerializedName("name") val name: String,
    @SerializedName("description") var description: String,
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("stock") val stock: Int,
    @SerializedName("price") var price: Double,
    @SerializedName("measure") val measure: MeasureResponse,
    @SerializedName("currency") val currency: CurrencyResponse,
    @SerializedName("saleStrategy") var strategy: ProductSaleStrategyResponse? = null,
    @SerializedName("productType") val productType: ProductTypeResponse? = null
) {
    companion object {
        fun mapToProduct(productResponse: ProductResponse): Product = Product(
            code = productResponse.code,
            name = productResponse.name,
            description = productResponse.description,
            imageUrl = productResponse.imageUrl,
            measure = MeasureResponse.mapToMeasure(productResponse.measure),
            currency = CurrencyResponse.mapToCurrency(productResponse.currency),
            price = productResponse.price,
            stock = productResponse.stock,
            productType = productResponse.productType?.let { type ->
                ProductTypeResponse.mapToProductType(type)
            } ?: ProductTypeResponse.getGenericProduct()
        ).apply {
            productResponse.strategy?.let { strategy ->
                addSaleProductStrategy(
                    ProductSaleStrategyResponse.mapToProductSaleStrategy(strategy)
                )
            }
        }

        fun mapToProduct(products: List<ProductResponse>): List<Product> =
            products.map { item -> mapToProduct(item) }
    }
}