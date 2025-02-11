package com.afoxplus.products.usecases.repositories

import com.afoxplus.products.entities.LandingProducts
import com.afoxplus.products.entities.Measure
import com.afoxplus.products.entities.Product
import com.afoxplus.products.entities.bussineslogic.SaleProductStrategy

internal interface ProductRepository {
    suspend fun fetchOffers(): List<Product>
    suspend fun fetch(restaurantCode: String, description: String): List<Product>
    suspend fun fetchSaleOffers(restaurantCode: String): List<Product>
    suspend fun fetchAppetizers(restaurantCode: String): List<Product>
    suspend fun fetchMenu(restaurantCode: String): List<Product>
    suspend fun find(code: String): Product
    suspend fun find(code: String, measure: Measure): Product
    suspend fun hasStock(code: String): Boolean
    suspend fun findSaleStrategy(code: String): SaleProductStrategy
    suspend fun fetchLandingProducts(restaurantCode: String): LandingProducts
}