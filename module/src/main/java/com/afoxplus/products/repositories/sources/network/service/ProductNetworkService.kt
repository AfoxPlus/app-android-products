package com.afoxplus.products.repositories.sources.network.service

import com.afoxplus.network.api.NetworkResult
import com.afoxplus.products.entities.Measure
import com.afoxplus.products.entities.Product
import com.afoxplus.products.entities.bussineslogic.SaleProductStrategy
import com.afoxplus.products.repositories.sources.network.ProductNetworkDataSource
import com.afoxplus.products.repositories.sources.network.api.ProductApiNetwork
import com.afoxplus.products.repositories.sources.network.api.request.ProductQueryRequest
import com.afoxplus.products.repositories.sources.network.api.response.ProductResponse
import com.afoxplus.products.repositories.sources.network.api.response.ProductSaleStrategyResponse
import com.afoxplus.uikit.result.UIKitResultState
import javax.inject.Inject

internal class ProductNetworkService @Inject constructor(
    private val productService: ProductApiNetwork
) : ProductNetworkDataSource {

    override suspend fun fetchOffers(): UIKitResultState<List<Product>> {
        return when (val response = productService.fetchHomeOffers()) {
            is NetworkResult.Success -> {
                val productList = response.data.payload.map { ProductResponse.mapToProduct(it) }
                UIKitResultState.Success(productList)
            }

            is NetworkResult.Error -> {
                UIKitResultState.Error(
                    response.message ?: API_PRODUCT_INTERNAL_ERROR,
                    response.code
                )
            }

            is NetworkResult.Exception -> {
                UIKitResultState.Error(API_PRODUCT_INTERNAL_ERROR)
            }
        }
    }

    override suspend fun fetch(
        restaurantCode: String,
        productName: String
    ): UIKitResultState<List<Product>> {
        val headerMap = mapOf(API_PRODUCT_HEADERS_RESTAURANT_CODE to restaurantCode)
        return when (val response = productService.fetch(
            headers = headerMap,
            query = ProductQueryRequest(productName = productName)
        )) {
            is NetworkResult.Success -> {
                val productList = response.data.payload.map { ProductResponse.mapToProduct(it) }
                UIKitResultState.Success(productList)
            }

            is NetworkResult.Error -> {
                UIKitResultState.Error(
                    response.message ?: API_PRODUCT_INTERNAL_ERROR,
                    response.code
                )
            }

            is NetworkResult.Exception -> {
                UIKitResultState.Error(API_PRODUCT_INTERNAL_ERROR)
            }
        }

    }

    override suspend fun fetchSaleOffers(restaurantCode: String): UIKitResultState<List<Product>> {
        val headerMap = mapOf(API_PRODUCT_HEADERS_RESTAURANT_CODE to restaurantCode)
        return when (val response = productService.fetchSaleOffers(headerMap)) {
            is NetworkResult.Success -> {
                val productList = response.data.payload.map { ProductResponse.mapToProduct(it) }
                UIKitResultState.Success(productList)
            }

            is NetworkResult.Error -> {
                UIKitResultState.Error(
                    response.message ?: API_PRODUCT_INTERNAL_ERROR,
                    response.code
                )
            }

            is NetworkResult.Exception -> {
                UIKitResultState.Error(API_PRODUCT_INTERNAL_ERROR)
            }
        }

    }

    override suspend fun fetchAppetizers(restaurantCode: String): UIKitResultState<List<Product>> {
        val headerMap = mapOf(API_PRODUCT_HEADERS_RESTAURANT_CODE to restaurantCode)
        return when (val response = productService.fetchAppetizers(headerMap)) {
            is NetworkResult.Success -> {
                val productList = response.data.payload.map { ProductResponse.mapToProduct(it) }
                UIKitResultState.Success(productList)
            }

            is NetworkResult.Error -> {
                UIKitResultState.Error(
                    response.message ?: API_PRODUCT_INTERNAL_ERROR,
                    response.code
                )
            }

            is NetworkResult.Exception -> {
                UIKitResultState.Error(API_PRODUCT_INTERNAL_ERROR)
            }
        }
    }

    override suspend fun fetchMenu(restaurantCode: String): UIKitResultState<List<Product>> {
        val headerMap = mapOf(API_PRODUCT_HEADERS_RESTAURANT_CODE to restaurantCode)
        return when (val response = productService.fetchMenu(headerMap)) {
            is NetworkResult.Success -> {
                val productList = response.data.payload.map { ProductResponse.mapToProduct(it) }
                UIKitResultState.Success(productList)
            }

            is NetworkResult.Error -> {
                UIKitResultState.Error(
                    response.message ?: API_PRODUCT_INTERNAL_ERROR,
                    response.code
                )
            }

            is NetworkResult.Exception -> {
                UIKitResultState.Error(API_PRODUCT_INTERNAL_ERROR)
            }
        }
    }

    override suspend fun find(productCode: String): UIKitResultState<Product> {
        return when (val response = productService.find(productCode)) {
            is NetworkResult.Success -> {
                val product = ProductResponse.mapToProduct(response.data.payload)
                UIKitResultState.Success(product)
            }

            is NetworkResult.Error -> {
                UIKitResultState.Error(
                    response.message ?: API_PRODUCT_INTERNAL_ERROR,
                    response.code
                )
            }

            is NetworkResult.Exception -> {
                UIKitResultState.Error(API_PRODUCT_INTERNAL_ERROR)
            }
        }
    }

    override suspend fun find(productCode: String, measure: Measure): UIKitResultState<Product> {
        return when (val response = productService.find(productCode, measure.code)) {
            is NetworkResult.Success -> {
                val product = ProductResponse.mapToProduct(response.data.payload)
                UIKitResultState.Success(product)
            }

            is NetworkResult.Error -> {
                UIKitResultState.Error(
                    response.message ?: API_PRODUCT_INTERNAL_ERROR,
                    response.code
                )
            }

            is NetworkResult.Exception -> {
                UIKitResultState.Error(API_PRODUCT_INTERNAL_ERROR)
            }
        }
    }

    override suspend fun hasStock(productCode: String): UIKitResultState<Boolean> {
        return when (val response = productService.hasStock(productCode)) {
            is NetworkResult.Success -> {
                UIKitResultState.Success(response.data.payload.hasStock)
            }

            is NetworkResult.Error -> {
                UIKitResultState.Error(
                    response.message ?: API_PRODUCT_INTERNAL_ERROR,
                    response.code
                )
            }

            is NetworkResult.Exception -> {
                UIKitResultState.Error(API_PRODUCT_INTERNAL_ERROR)
            }
        }
    }

    override suspend fun findSaleStrategy(productCode: String): UIKitResultState<SaleProductStrategy> {
        return when (val response = productService.findSaleStrategy(productCode)) {
            is NetworkResult.Success -> {
                val saleStrategy =
                    ProductSaleStrategyResponse.mapToProductSaleStrategy(response.data.payload)
                UIKitResultState.Success(saleStrategy)
            }

            is NetworkResult.Error -> {
                UIKitResultState.Error(
                    response.message ?: API_PRODUCT_INTERNAL_ERROR,
                    response.code
                )
            }

            is NetworkResult.Exception -> {
                UIKitResultState.Error(API_PRODUCT_INTERNAL_ERROR)
            }
        }
    }

    companion object {
        private const val API_PRODUCT_INTERNAL_ERROR: String = "API Products Internal Error"
        private const val API_PRODUCT_HEADERS_RESTAURANT_CODE: String = "restaurant_code"
    }
}