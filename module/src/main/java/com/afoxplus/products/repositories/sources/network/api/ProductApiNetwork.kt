package com.afoxplus.products.repositories.sources.network.api

import com.afoxplus.network.response.BaseResponse
import com.afoxplus.products.repositories.sources.network.api.request.ProductQueryRequest
import com.afoxplus.products.repositories.sources.network.api.response.ProductResponse
import com.afoxplus.products.repositories.sources.network.api.response.ProductSaleStrategyResponse
import com.afoxplus.products.repositories.sources.network.api.response.ProductStockResponse
import retrofit2.Response
import retrofit2.http.*

internal interface ProductApiNetwork {
    companion object {
        const val PATH_PRODUCT = "product"
        const val PATH_STOCK = "stock"
        const val PATH_STRATEGY = "strategy"
    }

    @POST("$PATH_PRODUCT/filter")
    suspend fun fetch(
        @HeaderMap headers: Map<String, String>,
        @Body query: ProductQueryRequest
    ): Response<BaseResponse<List<ProductResponse>>>

    @GET("$PATH_PRODUCT/sale_offers")
    suspend fun fetchSaleOffers(@HeaderMap headers: Map<String, String>): Response<BaseResponse<List<ProductResponse>>>

    @GET("$PATH_PRODUCT/home_offers")
    suspend fun fetchHomeOffers(): Response<BaseResponse<List<ProductResponse>>>

    @GET("$PATH_PRODUCT/appetizers")
    suspend fun fetchAppetizers(@HeaderMap headers: Map<String, String>): Response<BaseResponse<List<ProductResponse>>>

    @GET("$PATH_PRODUCT/menu")
    suspend fun fetchMenu(@HeaderMap headers: Map<String, String>): Response<BaseResponse<List<ProductResponse>>>

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