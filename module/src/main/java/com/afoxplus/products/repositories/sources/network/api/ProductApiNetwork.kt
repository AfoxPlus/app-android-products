package com.afoxplus.products.repositories.sources.network.api

import com.afoxplus.network.annotations.ServiceClient
import com.afoxplus.network.api.NetworkResult
import com.afoxplus.network.api.UrlProvider
import com.afoxplus.network.response.BaseResponse
import com.afoxplus.products.repositories.sources.network.api.request.ProductQueryRequest
import com.afoxplus.products.repositories.sources.network.api.response.ProductResponse
import com.afoxplus.products.repositories.sources.network.api.response.ProductSaleStrategyResponse
import com.afoxplus.products.repositories.sources.network.api.response.ProductStockResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.POST
import retrofit2.http.Path

@ServiceClient(type = UrlProvider.Type.API_PRODUCTS)
internal interface ProductApiNetwork {
    companion object {
        const val PATH_PRODUCT = "product"
        const val PATH_STOCK = "stock"
        const val PATH_STRATEGY = "strategy"
        const val PATH_SEARCH = "search"
    }

    @POST("$PATH_PRODUCT/filter")
    suspend fun fetch(
        @HeaderMap headers: Map<String, String>,
        @Body query: ProductQueryRequest
    ): NetworkResult<BaseResponse<List<ProductResponse>>>

    @GET("$PATH_PRODUCT/sale_offer")
    suspend fun fetchSaleOffers(@HeaderMap headers: Map<String, String>): NetworkResult<BaseResponse<List<ProductResponse>>>

    @GET("$PATH_PRODUCT/home_offer")
    suspend fun fetchHomeOffers(): NetworkResult<BaseResponse<List<ProductResponse>>>

    @GET("$PATH_PRODUCT/appetizer")
    suspend fun fetchAppetizers(@HeaderMap headers: Map<String, String>): NetworkResult<BaseResponse<List<ProductResponse>>>

    @GET("$PATH_PRODUCT/menu")
    suspend fun fetchMenu(@HeaderMap headers: Map<String, String>): NetworkResult<BaseResponse<List<ProductResponse>>>

    @GET("$PATH_PRODUCT/$PATH_SEARCH/{code}")
    suspend fun find(@Path("code") code: String): NetworkResult<BaseResponse<ProductResponse>>

    @GET("$PATH_PRODUCT/$PATH_SEARCH/{code}/{measure}")
    suspend fun find(
        @Path("code") code: String,
        @Path("measure") measure: String
    ): NetworkResult<BaseResponse<ProductResponse>>

    @GET("$PATH_PRODUCT/$PATH_STOCK/{code}")
    suspend fun hasStock(@Path("code") code: String): NetworkResult<BaseResponse<ProductStockResponse>>

    @GET("$PATH_PRODUCT/$PATH_STRATEGY/{product_code}")
    suspend fun findSaleStrategy(@Path("product_code") productCode: String): NetworkResult<BaseResponse<ProductSaleStrategyResponse>>
}