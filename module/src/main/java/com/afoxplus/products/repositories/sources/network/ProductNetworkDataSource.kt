package com.afoxplus.products.repositories.sources.network

import com.afoxplus.products.entities.Measure
import com.afoxplus.products.entities.Product
import com.afoxplus.products.entities.bussineslogic.SaleProductStrategy

internal interface ProductNetworkDataSource {
    suspend fun fetchHomeOffers(): List<Product>
    suspend fun fetch(description: String): List<Product>
    suspend fun fetchSaleOffers(): List<Product>
    suspend fun fetchAppetizers(): List<Product>
    suspend fun fetchMenu(): List<Product>
    suspend fun find(code: String): Product
    suspend fun find(code: String, measure: Measure): Product
    suspend fun hasStock(code: String): Boolean
    suspend fun findSaleStrategy(code: String): SaleProductStrategy
}