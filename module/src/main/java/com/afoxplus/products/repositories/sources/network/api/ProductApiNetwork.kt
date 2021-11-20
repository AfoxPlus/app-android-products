package com.afoxplus.products.repositories.sources.network.api

import com.afoxplus.network.response.BaseResponse
import com.afoxplus.products.repositories.sources.network.api.request.ProductFilterRequest
import com.afoxplus.products.repositories.sources.network.api.response.ProductResponse
import com.afoxplus.products.repositories.sources.network.api.response.ProductSaleStrategyResponse
import com.afoxplus.products.repositories.sources.network.api.response.ProductStockResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

internal interface ProductApiNetwork {
    companion object {
        const val PATH_PRODUCT = "product"
        const val PATH_STOCK = "stock"
        const val PATH_STRATEGY = "strategy"
    }

    @POST("$PATH_PRODUCT/filter")
    suspend fun fetch(@Body filter: ProductFilterRequest): Response<BaseResponse<List<ProductResponse>>>

    @GET("$PATH_PRODUCT/search/{code}")
    suspend fun find(@Path("code") code: String): Response<BaseResponse<ProductResponse>>

    @GET("$PATH_PRODUCT/search/{code}/{measure}")
    suspend fun find(
        @Path("code") code: String,
        @Path("measure") measure: String
    ): Response<BaseResponse<ProductResponse>>

    @GET("$PATH_PRODUCT/$PATH_STOCK/{code}")
    suspend fun hasStock(@Path("code") code: String): Response<BaseResponse<ProductStockResponse>>

    @GET("$PATH_PRODUCT/$PATH_STRATEGY/{product_code}")
    suspend fun findSaleStrategy(@Path("product_code") productCode: String): Response<BaseResponse<ProductSaleStrategyResponse>>
}