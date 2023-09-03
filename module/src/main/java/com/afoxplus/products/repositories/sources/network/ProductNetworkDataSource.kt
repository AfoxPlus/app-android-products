package com.afoxplus.products.repositories.sources.network

import com.afoxplus.products.entities.Measure
import com.afoxplus.products.entities.Product
import com.afoxplus.products.entities.bussineslogic.SaleProductStrategy
import com.afoxplus.uikit.result.UIKitResultState

internal interface ProductNetworkDataSource {
    suspend fun fetchOffers(): UIKitResultState<List<Product>>
    suspend fun fetch(restaurantCode: String, productName: String): UIKitResultState<List<Product>>
    suspend fun fetchSaleOffers(restaurantCode: String,): UIKitResultState<List<Product>>
    suspend fun fetchAppetizers(restaurantCode: String,): UIKitResultState<List<Product>>
    suspend fun fetchMenu(restaurantCode: String,): UIKitResultState<List<Product>>
    suspend fun find(productCode: String): UIKitResultState<Product>
    suspend fun find(productCode: String, measure: Measure): UIKitResultState<Product>
    suspend fun hasStock(productCode: String): UIKitResultState<Boolean>
    suspend fun findSaleStrategy(productCode: String): UIKitResultState<SaleProductStrategy>
}