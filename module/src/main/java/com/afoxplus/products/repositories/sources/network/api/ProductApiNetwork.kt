package com.afoxplus.products.repositories.sources.network.api

import com.afoxplus.network.annotations.ServiceClient
import com.afoxplus.network.api.UrlProvider
import com.afoxplus.network.response.BaseResponse
import com.afoxplus.products.repositories.sources.network.api.request.ProductQueryRequest
import com.afoxplus.products.repositories.sources.network.api.response.LandingProductsResponse
import com.afoxplus.products.repositories.sources.network.api.response.ProductResponse
import com.afoxplus.products.repositories.sources.network.api.response.ProductSaleStrategyResponse
import com.afoxplus.products.repositories.sources.network.api.response.ProductStockResponse
import retrofit2.Response
import retrofit2.http.*

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
    ): Response<BaseResponse<List<ProductResponse>>>

    @GET("$PATH_PRODUCT/sale_offer")
    suspend fun fetchSaleOffers(@HeaderMap headers: Map<String, String>): Response<BaseResponse<List<ProductResponse>>>

    @GET("$PATH_PRODUCT/home_offer")
    suspend fun fetchHomeOffers(): Response<BaseResponse<List<ProductResponse>>>

    @GET("$PATH_PRODUCT/appetizer")
    suspend fun fetchAppetizers(@HeaderMap headers: Map<String, String>): Response<BaseResponse<List<ProductResponse>>>

    @GET("$PATH_PRODUCT/menu")
    suspend fun fetchMenu(@HeaderMap headers: Map<String, String>): Response<BaseResponse<List<ProductResponse>>>

    @GET("$PATH_PRODUCT/$PATH_SEARCH/{code}")
    suspend fun find(@Path("code") code: String): Response<BaseResponse<ProductResponse>>

    @GET("$PATH_PRODUCT/$PATH_SEARCH/{code}/{measure}")
    suspend fun find(
        @Path("code") code: String,
        @Path("measure") measure: String
    ): Response<BaseResponse<ProductResponse>>

    @GET("$PATH_PRODUCT/$PATH_STOCK/{code}")
    suspend fun hasStock(@Path("code") code: String): Response<BaseResponse<ProductStockResponse>>

    @GET("$PATH_PRODUCT/$PATH_STRATEGY/{product_code}")
    suspend fun findSaleStrategy(@Path("product_code") productCode: String): Response<BaseResponse<ProductSaleStrategyResponse>>

    @GET("v1/$PATH_PRODUCT/menu")
    suspend fun fetchLandingProducts(@HeaderMap headers: Map<String, String>): Response<BaseResponse<LandingProductsResponse>>

}