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
import com.afoxplus.uikit.result.ErrorMessage
import com.afoxplus.uikit.result.ErrorType
import com.afoxplus.uikit.result.ResultState
import javax.inject.Inject

internal class ProductNetworkService @Inject constructor(
    private val productService: ProductApiNetwork
) : ProductNetworkDataSource {

    override suspend fun fetchOffers(): ResultState<List<Product>> {
        return when (val response = productService.fetchHomeOffers()) {
            is NetworkResult.Success -> {
                val productList = response.data.payload.map { ProductResponse.mapToProduct(it) }
                ResultState.Success(productList)
            }

            is NetworkResult.Error -> {
                ResultState.Error(
                    ErrorMessage(
                        code = response.code,
                        title = "",
                        message = response.message ?: API_PRODUCT_INTERNAL_ERROR,
                        errorType = ErrorType.ERROR
                    )
                )
            }

            is NetworkResult.Exception -> {
                ResultState.Error(
                    ErrorMessage(
                        title = "",
                        message = API_PRODUCT_INTERNAL_ERROR,
                        errorType = ErrorType.EXCEPTION
                    )
                )
            }
        }
    }

    override suspend fun fetch(
        restaurantCode: String,
        productName: String
    ): ResultState<List<Product>> {
        val headerMap = mapOf(API_PRODUCT_HEADERS_RESTAURANT_CODE to restaurantCode)
        return when (val response = productService.fetch(
            headers = headerMap,
            query = ProductQueryRequest(productName = productName)
        )) {
            is NetworkResult.Success -> {
                val productList = response.data.payload.map { ProductResponse.mapToProduct(it) }
                ResultState.Success(productList)
            }

            is NetworkResult.Error -> {
                ResultState.Error(
                    ErrorMessage(
                        code = response.code,
                        title = "",
                        message = response.message ?: API_PRODUCT_INTERNAL_ERROR,
                        errorType = ErrorType.ERROR
                    )
                )
            }

            is NetworkResult.Exception -> {
                ResultState.Error(
                    ErrorMessage(
                        title = "",
                        message = API_PRODUCT_INTERNAL_ERROR,
                        errorType = ErrorType.EXCEPTION
                    )
                )
            }
        }

    }

    override suspend fun fetchSaleOffers(restaurantCode: String): ResultState<List<Product>> {
        val headerMap = mapOf(API_PRODUCT_HEADERS_RESTAURANT_CODE to restaurantCode)
        return when (val response = productService.fetchSaleOffers(headerMap)) {
            is NetworkResult.Success -> {
                val productList = response.data.payload.map { ProductResponse.mapToProduct(it) }
                ResultState.Success(productList)
            }

            is NetworkResult.Error -> {
                ResultState.Error(
                    ErrorMessage(
                        code = response.code,
                        title = "",
                        message = response.message ?: API_PRODUCT_INTERNAL_ERROR,
                        errorType = ErrorType.ERROR
                    )
                )
            }

            is NetworkResult.Exception -> {
                ResultState.Error(
                    ErrorMessage(
                        title = "",
                        message = API_PRODUCT_INTERNAL_ERROR,
                        errorType = ErrorType.EXCEPTION
                    )
                )
            }
        }

    }

    override suspend fun fetchAppetizers(restaurantCode: String): ResultState<List<Product>> {
        val headerMap = mapOf(API_PRODUCT_HEADERS_RESTAURANT_CODE to restaurantCode)
        return when (val response = productService.fetchAppetizers(headerMap)) {
            is NetworkResult.Success -> {
                val productList = response.data.payload.map { ProductResponse.mapToProduct(it) }
                ResultState.Success(productList)
            }

            is NetworkResult.Error -> {
                ResultState.Error(
                    ErrorMessage(
                        code = response.code,
                        title = "",
                        message = response.message ?: API_PRODUCT_INTERNAL_ERROR,
                        errorType = ErrorType.ERROR
                    )
                )
            }

            is NetworkResult.Exception -> {
                ResultState.Error(
                    ErrorMessage(
                        title = "",
                        message = API_PRODUCT_INTERNAL_ERROR,
                        errorType = ErrorType.EXCEPTION
                    )
                )
            }
        }
    }

    override suspend fun fetchMenu(restaurantCode: String): ResultState<List<Product>> {
        val headerMap = mapOf(API_PRODUCT_HEADERS_RESTAURANT_CODE to restaurantCode)
        return when (val response = productService.fetchMenu(headerMap)) {
            is NetworkResult.Success -> {
                val productList = response.data.payload.map { ProductResponse.mapToProduct(it) }
                ResultState.Success(productList)
            }

            is NetworkResult.Error -> {
                ResultState.Error(
                    ErrorMessage(
                        code = response.code,
                        title = "",
                        message = response.message ?: API_PRODUCT_INTERNAL_ERROR,
                        errorType = ErrorType.ERROR
                    )
                )
            }

            is NetworkResult.Exception -> {
                response.exception.printStackTrace()
                ResultState.Error(
                    ErrorMessage(
                        title = "",
                        message = API_PRODUCT_INTERNAL_ERROR,
                        errorType = ErrorType.EXCEPTION
                    )
                )
            }
        }
    }

    override suspend fun find(productCode: String): ResultState<Product> {
        return when (val response = productService.find(productCode)) {
            is NetworkResult.Success -> {
                val product = ProductResponse.mapToProduct(response.data.payload)
                ResultState.Success(product)
            }

            is NetworkResult.Error -> {
                ResultState.Error(
                    ErrorMessage(
                        code = response.code,
                        title = "",
                        message = response.message ?: API_PRODUCT_INTERNAL_ERROR,
                        errorType = ErrorType.ERROR
                    )
                )
            }

            is NetworkResult.Exception -> {
                ResultState.Error(
                    ErrorMessage(
                        title = "",
                        message = API_PRODUCT_INTERNAL_ERROR,
                        errorType = ErrorType.EXCEPTION
                    )
                )
            }
        }
    }

    override suspend fun find(productCode: String, measure: Measure): ResultState<Product> {
        return when (val response = productService.find(productCode, measure.code)) {
            is NetworkResult.Success -> {
                val product = ProductResponse.mapToProduct(response.data.payload)
                ResultState.Success(product)
            }

            is NetworkResult.Error -> {
                ResultState.Error(
                    ErrorMessage(
                        code = response.code,
                        title = "",
                        message = response.message ?: API_PRODUCT_INTERNAL_ERROR,
                        errorType = ErrorType.ERROR
                    )
                )
            }

            is NetworkResult.Exception -> {
                ResultState.Error(
                    ErrorMessage(
                        title = "",
                        message = API_PRODUCT_INTERNAL_ERROR,
                        errorType = ErrorType.EXCEPTION
                    )
                )
            }
        }
    }

    override suspend fun hasStock(productCode: String): ResultState<Boolean> {
        return when (val response = productService.hasStock(productCode)) {
            is NetworkResult.Success -> {
                ResultState.Success(response.data.payload.hasStock)
            }

            is NetworkResult.Error -> {
                ResultState.Error(
                    ErrorMessage(
                        code = response.code,
                        title = "",
                        message = response.message ?: API_PRODUCT_INTERNAL_ERROR,
                        errorType = ErrorType.ERROR
                    )
                )
            }

            is NetworkResult.Exception -> {
                ResultState.Error(
                    ErrorMessage(
                        title = "",
                        message = API_PRODUCT_INTERNAL_ERROR,
                        errorType = ErrorType.EXCEPTION
                    )
                )
            }
        }
    }

    override suspend fun findSaleStrategy(productCode: String): ResultState<SaleProductStrategy> {
        return when (val response = productService.findSaleStrategy(productCode)) {
            is NetworkResult.Success -> {
                val saleStrategy =
                    ProductSaleStrategyResponse.mapToProductSaleStrategy(response.data.payload)
                ResultState.Success(saleStrategy)
            }

            is NetworkResult.Error -> {
                ResultState.Error(
                    ErrorMessage(
                        code = response.code,
                        title = "",
                        message = response.message ?: API_PRODUCT_INTERNAL_ERROR,
                        errorType = ErrorType.ERROR
                    )
                )
            }

            is NetworkResult.Exception -> {
                ResultState.Error(
                    ErrorMessage(
                        title = "",
                        message = API_PRODUCT_INTERNAL_ERROR,
                        errorType = ErrorType.EXCEPTION
                    )
                )
            }
        }
    }

    companion object {
        private const val API_PRODUCT_INTERNAL_ERROR: String = "API Products Internal Error"
        private const val API_PRODUCT_HEADERS_RESTAURANT_CODE: String = "restaurant_code"
    }
}