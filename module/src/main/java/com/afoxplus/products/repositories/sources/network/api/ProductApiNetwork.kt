package com.afoxplus.products.repositories.sources.network.api

import com.afoxplus.products.repositories.sources.network.api.response.ProductResponse
import com.afoxplus.products.repositories.sources.network.api.response.ProductSaleStrategyResponse
import com.afoxplus.products.repositories.sources.network.api.response.ProductStockResponse
import com.afoxplus.uikit.service.annotations.MockService
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

internal interface ProductApiNetwork {
    companion object {
        const val PATH_PRODUCT = "product"
        const val PATH_STOCK = "stock"
        const val PATH_STRATEGY = "strategy"
    }

    @MockService(jsonFileName = "mocks/fetchProduct.json")
    @GET("$PATH_PRODUCT/{description}")
    suspend fun fetch(@Path("description") description: String): Response<List<ProductResponse>>

    @MockService(jsonFileName = "mocks/findProductByCode.json")
    @GET("$PATH_PRODUCT/{code}")
    suspend fun find(@Path("code") code: String): Response<ProductResponse>

    @GET("$PATH_PRODUCT/{code}/{measure}")
    suspend fun find(
        @Path("code") code: String,
        @Path("measure") measure: String
    ): Response<ProductResponse>

    @MockService(jsonFileName = "mocks/hasStockProduct.json")
    @GET("$PATH_PRODUCT/$PATH_STOCK/{code}")
    suspend fun hasStock(@Path("code") code: String): Response<ProductStockResponse>

    @MockService(jsonFileName = "mocks/findSaleStrategy.json")
    @GET("$PATH_PRODUCT/$PATH_STRATEGY/{product_code}")
    suspend fun findSaleStrategy(@Path("product_code") productCode: String): Response<ProductSaleStrategyResponse>
}