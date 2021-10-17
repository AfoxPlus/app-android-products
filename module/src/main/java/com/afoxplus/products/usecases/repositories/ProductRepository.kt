package com.afoxplus.products.usecases.repositories

import com.afoxplus.products.entities.Measure
import com.afoxplus.products.entities.Product
import com.afoxplus.products.entities.bussineslogic.SaleProductStrategy

internal interface ProductRepository {
    suspend fun fetch(description: String): List<Product>
    suspend fun find(code: String): Product
    suspend fun find(code: String, measure: Measure): Product
    suspend fun hasStock(code: String): Boolean
    suspend fun findSaleStrategy(code: String): SaleProductStrategy
}