package com.afoxplus.products.repositories.sources.network

import com.afoxplus.products.entities.Measure
import com.afoxplus.products.entities.Product
import com.afoxplus.products.entities.bussineslogic.SaleProductStrategy
import com.afoxplus.uikit.result.ResultState

internal interface ProductNetworkDataSource {
    suspend fun fetchOffers(): ResultState<List<Product>>
    suspend fun fetch(restaurantCode: String, productName: String): ResultState<List<Product>>
    suspend fun fetchSaleOffers(restaurantCode: String,): ResultState<List<Product>>
    suspend fun fetchAppetizers(restaurantCode: String,): ResultState<List<Product>>
    suspend fun fetchMenu(restaurantCode: String,): ResultState<List<Product>>
    suspend fun find(productCode: String): ResultState<Product>
    suspend fun find(productCode: String, measure: Measure): ResultState<Product>
    suspend fun hasStock(productCode: String): ResultState<Boolean>
    suspend fun findSaleStrategy(productCode: String): ResultState<SaleProductStrategy>
}