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

internal class ProductNetworkService @Inject constructor(
    private val productService: ProductApiNetwork
) :
    ProductNetworkDataSource {

    override suspend fun fetchOffers(): List<Product> {
        val response = productService.fetchHomeOffers()
        var productList: List<Product> = arrayListOf()
        response.map { productList = ProductResponse.mapToProduct(it.payload) }
        return productList
    }

    override suspend fun fetch(restaurantCode: String, productName: String): List<Product> {
        val headerMap = mapOf(API_PRODUCT_HEADERS_RESTAURANT_CODE to restaurantCode)
        val response = productService.fetch(
            headers = headerMap,
            query = ProductQueryRequest(productName = productName)
        )
        var productList: List<Product> = arrayListOf()
        response.map { productList = ProductResponse.mapToProduct(it.payload) }
        return productList
    }

    override suspend fun fetchSaleOffers(restaurantCode: String): List<Product> {
        val headerMap = mapOf(API_PRODUCT_HEADERS_RESTAURANT_CODE to restaurantCode)
        val response = productService.fetchSaleOffers(headerMap)
        var productList: List<Product> = arrayListOf()
        response.map { productList = ProductResponse.mapToProduct(it.payload) }
        return productList
    }

    override suspend fun fetchAppetizers(restaurantCode: String): List<Product> {
        val headerMap = mapOf(API_PRODUCT_HEADERS_RESTAURANT_CODE to restaurantCode)
        val response = productService.fetchAppetizers(headerMap)
        var productList: List<Product> = arrayListOf()
        response.map { productList = ProductResponse.mapToProduct(it.payload) }
        return productList
    }

    override suspend fun fetchMenu(restaurantCode: String): List<Product> {
        val headerMap = mapOf(API_PRODUCT_HEADERS_RESTAURANT_CODE to restaurantCode)
        val response = productService.fetchMenu(headerMap)
        var productList: List<Product> = arrayListOf()
        response.map { productList = ProductResponse.mapToProduct(it.payload) }
        return productList
    }

    override suspend fun find(productCode: String): Product {
        val response = productService.find(productCode)
        var product: Product? = null
        response.map { product = ProductResponse.mapToProduct(it.payload) }
        return product ?: throw IOException(API_PRODUCT_INTERNAL_ERROR)
    }

    override suspend fun find(productCode: String, measure: Measure): Product {
        val response = productService.find(productCode, measure.code)
        var product: Product? = null
        response.map { product = ProductResponse.mapToProduct(it.payload) }
        return product
            ?: throw IOException(API_PRODUCT_INTERNAL_ERROR)
    }

    override suspend fun hasStock(productCode: String): Boolean {
        val response = productService.hasStock(productCode)
        var hasStock = false
        response.map { hasStock = it.payload.hasStock }
        return hasStock
    }

    override suspend fun findSaleStrategy(productCode: String): SaleProductStrategy {
        val response = productService.findSaleStrategy(productCode)
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