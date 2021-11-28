package com.afoxplus.products.repositories.sources.network.service


import com.afoxplus.network.extensions.map
import com.afoxplus.products.entities.Measure
import com.afoxplus.products.entities.Product
import com.afoxplus.products.entities.bussineslogic.SaleProductStrategy
import com.afoxplus.products.repositories.sources.network.ProductNetworkDataSource
import com.afoxplus.products.repositories.sources.network.api.ProductApiNetwork
import com.afoxplus.products.repositories.sources.network.api.request.ProductQueryRequest
import com.afoxplus.products.repositories.sources.network.api.response.ProductResponse
import com.afoxplus.products.repositories.sources.network.api.response.ProductSaleStrategyResponse
import java.io.IOException
import javax.inject.Inject

internal class ProductNetworkService @Inject constructor(private val productService: ProductApiNetwork) :
    ProductNetworkDataSource {

    override suspend fun fetch(description: String): List<Product> {
        val headerMap = mapOf(API_PRODUCT_HEADERS_RESTAURANT_CODE to "61a19c440b6de1476436de4a")
        val response = productService.fetch(
            headers = headerMap,
            query = ProductQueryRequest(productName = description)
        )
        var productList: List<Product> = arrayListOf()
        response.map { productList = ProductResponse.mapToProduct(it.payload) }
        return productList
    }

    override suspend fun find(code: String): Product {
        val response = productService.find(code)
        var product: Product? = null
        response.map { product = ProductResponse.mapToProduct(it.payload) }
        return product ?: throw IOException(API_PRODUCT_INTERNAL_ERROR)
    }

    override suspend fun find(code: String, measure: Measure): Product {
        val response = productService.find(code, measure.code)
        var product: Product? = null
        response.map { product = ProductResponse.mapToProduct(it.payload) }
        return product
            ?: throw IOException(API_PRODUCT_INTERNAL_ERROR)
    }

    override suspend fun hasStock(code: String): Boolean {
        val response = productService.hasStock(code)
        var hasStock = false
        response.map { hasStock = it.payload.hasStock }
        return hasStock
    }

    override suspend fun findSaleStrategy(code: String): SaleProductStrategy {
        val response = productService.findSaleStrategy(code)
        var saleStrategy: SaleProductStrategy? = null
        response.map {
            saleStrategy = ProductSaleStrategyResponse.mapToProductSaleStrategy(it.payload)
        }
        return saleStrategy ?: throw IOException(API_PRODUCT_INTERNAL_ERROR)
    }

    companion object {
        private const val API_PRODUCT_INTERNAL_ERROR: String = "API Products Internal Error"
        private const val API_PRODUCT_HEADERS_RESTAURANT_CODE: String = "restaurant_code"
    }
}