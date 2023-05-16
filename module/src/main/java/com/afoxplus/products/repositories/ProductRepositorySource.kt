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
    override suspend fun fetchHomeOffers(): List<Product> =
        productNetworkDataSource.fetchHomeOffers()

    override suspend fun fetch(description: String): List<Product> =
        productNetworkDataSource.fetch(description)

    override suspend fun fetchSaleOffers(): List<Product> =
        productNetworkDataSource.fetchSaleOffers()

    override suspend fun fetchAppetizers(): List<Product> =
        productNetworkDataSource.fetchAppetizers()

    override suspend fun fetchMenu(): List<Product> = productNetworkDataSource.fetchMenu()

    override suspend fun find(code: String): Product = productNetworkDataSource.find(code)

    override suspend fun find(code: String, measure: Measure): Product =
        productNetworkDataSource.find(code, measure)

    override suspend fun hasStock(code: String): Boolean =
        productNetworkDataSource.hasStock(code)

    override suspend fun findSaleStrategy(code: String): SaleProductStrategy =
        productNetworkDataSource.findSaleStrategy(code)

}