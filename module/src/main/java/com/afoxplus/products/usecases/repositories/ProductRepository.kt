package com.afoxplus.products.usecases.repositories

import com.afoxplus.products.entities.Measure
import com.afoxplus.products.entities.Product
import com.afoxplus.products.entities.bussineslogic.SaleProductStrategy
import com.afoxplus.uikit.result.UIKitResultState

internal interface ProductRepository {
    suspend fun fetchOffers(): UIKitResultState<List<Product>>
    suspend fun fetch(restaurantCode: String, description: String): UIKitResultState<List<Product>>
    suspend fun fetchSaleOffers(restaurantCode: String): UIKitResultState<List<Product>>
    suspend fun fetchAppetizers(restaurantCode: String): UIKitResultState<List<Product>>
    suspend fun fetchMenu(restaurantCode: String): UIKitResultState<List<Product>>
    suspend fun find(code: String): UIKitResultState<Product>
    suspend fun find(code: String, measure: Measure): UIKitResultState<Product>
    suspend fun hasStock(code: String): UIKitResultState<Boolean>
    suspend fun findSaleStrategy(code: String): UIKitResultState<SaleProductStrategy>
}