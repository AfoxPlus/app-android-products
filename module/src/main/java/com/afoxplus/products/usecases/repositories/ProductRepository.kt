package com.afoxplus.products.usecases.repositories

import com.afoxplus.products.entities.Measure
import com.afoxplus.products.entities.Product
import com.afoxplus.products.entities.bussineslogic.SaleProductStrategy
import com.afoxplus.uikit.result.ResultState

internal interface ProductRepository {
    suspend fun fetchOffers(): ResultState<List<Product>>
    suspend fun fetch(restaurantCode: String, description: String): ResultState<List<Product>>
    suspend fun fetchSaleOffers(restaurantCode: String): ResultState<List<Product>>
    suspend fun fetchAppetizers(restaurantCode: String): ResultState<List<Product>>
    suspend fun fetchMenu(restaurantCode: String): ResultState<List<Product>>
    suspend fun find(code: String): ResultState<Product>
    suspend fun find(code: String, measure: Measure): ResultState<Product>
    suspend fun hasStock(code: String): ResultState<Boolean>
    suspend fun findSaleStrategy(code: String): ResultState<SaleProductStrategy>
}