package com.afoxplus.products.repositories.sources.network

import com.afoxplus.products.entities.Measure
import com.afoxplus.products.entities.Product
import com.afoxplus.products.entities.bussineslogic.SaleProductStrategy

internal interface ProductNetworkDataSource {
    suspend fun fetchOffers(): List<Product>
    suspend fun fetch(restaurantCode: String, productName: String): List<Product>
    suspend fun fetchSaleOffers(restaurantCode: String,): List<Product>
    suspend fun fetchAppetizers(restaurantCode: String,): List<Product>
    suspend fun fetchMenu(restaurantCode: String,): List<Product>
    suspend fun find(productCode: String): Product
    suspend fun find(productCode: String, measure: Measure): Product
    suspend fun hasStock(productCode: String): Boolean
    suspend fun findSaleStrategy(productCode: String): SaleProductStrategy
}