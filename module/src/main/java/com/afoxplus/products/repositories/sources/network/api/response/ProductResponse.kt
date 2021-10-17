package com.afoxplus.products.repositories.sources.network.api.response

import com.afoxplus.products.entities.Currency
import com.afoxplus.products.entities.Measure
import com.afoxplus.products.entities.Product
import com.google.gson.annotations.SerializedName

internal data class ProductResponse(
    @SerializedName("code") val code: String,
    @SerializedName("name") val name: String,
    @SerializedName("description") var description: String,
    @SerializedName("imageUrl") val imageUrl: String,
    @SerializedName("measureCode") val measureCode: String,
    @SerializedName("measureValue") val measureValue: String,
    @SerializedName("currencyCode") val currencyCode: String,
    @SerializedName("currencyValue") val currencyValue: String,
    @SerializedName("stock") val stock: Int,
    @SerializedName("price") var price: Double,
    @SerializedName("saleStrategy") var strategy: ProductSaleStrategyResponse
) {
    companion object {
        fun mapToProduct(productResponse: ProductResponse): Product = Product(
            code = productResponse.code,
            name = productResponse.name,
            description = productResponse.description,
            imageUrl = productResponse.imageUrl,
            measure = Measure(productResponse.measureCode, productResponse.measureValue),
            currency = Currency(productResponse.currencyCode, productResponse.currencyValue),
            price = productResponse.price,
            stock = productResponse.stock
        ).apply {
            addSaleProductStrategy(
                ProductSaleStrategyResponse.mapToProductSaleStrategy(
                    productResponse.strategy
                )
            )
        }

        fun mapToProduct(products: List<ProductResponse>): List<Product> =
            products.map { item -> mapToProduct(item) }
    }
}