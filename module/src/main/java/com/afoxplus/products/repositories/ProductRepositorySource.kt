package com.afoxplus.products.repositories

import com.afoxplus.products.entities.Measure
import com.afoxplus.products.entities.Product
import com.afoxplus.products.entities.bussineslogic.SaleProductStrategy
import com.afoxplus.products.repositories.sources.network.ProductNetworkDataSource
import com.afoxplus.products.usecases.repositories.ProductRepository
import javax.inject.Inject

internal class ProductRepositorySource @Inject constructor(
    private val productNetworkDataSource: ProductNetworkDataSource
) : ProductRepository {
    override suspend fun fetchOffers(): List<Product> =
        productNetworkDataSource.fetchOffers()

    override suspend fun fetch(restaurantCode: String, description: String): List<Product> =
        productNetworkDataSource.fetch(restaurantCode, description)

    override suspend fun fetchSaleOffers(restaurantCode: String): List<Product> =
        productNetworkDataSource.fetchSaleOffers(restaurantCode)

    override suspend fun fetchAppetizers(restaurantCode: String): List<Product> =
        productNetworkDataSource.fetchAppetizers(restaurantCode)

    override suspend fun fetchMenu(restaurantCode: String): List<Product> =
        productNetworkDataSource.fetchMenu(restaurantCode)

    override suspend fun find(code: String): Product = productNetworkDataSource.find(code)

    override suspend fun find(code: String, measure: Measure): Product =
        productNetworkDataSource.find(code, measure)

    override suspend fun hasStock(code: String): Boolean =
        productNetworkDataSource.hasStock(code)

    override suspend fun findSaleStrategy(code: String): SaleProductStrategy =
        productNetworkDataSource.findSaleStrategy(code)

}