package com.afoxplus.products.repositories

import com.afoxplus.products.entities.Measure
import com.afoxplus.products.entities.Product
import com.afoxplus.products.entities.bussineslogic.SaleProductStrategy
import com.afoxplus.products.repositories.sources.network.ProductNetworkDataSource
import com.afoxplus.products.usecases.repositories.ProductRepository
import com.afoxplus.uikit.result.ResultState
import javax.inject.Inject

internal class ProductRepositorySource @Inject constructor(
    private val productNetworkDataSource: ProductNetworkDataSource
) : ProductRepository {
    override suspend fun fetchOffers(): ResultState<List<Product>> =
        productNetworkDataSource.fetchOffers()

    override suspend fun fetch(
        restaurantCode: String,
        description: String
    ): ResultState<List<Product>> =
        productNetworkDataSource.fetch(restaurantCode, description)

    override suspend fun fetchSaleOffers(restaurantCode: String): ResultState<List<Product>> =
        productNetworkDataSource.fetchSaleOffers(restaurantCode)

    override suspend fun fetchAppetizers(restaurantCode: String): ResultState<List<Product>> =
        productNetworkDataSource.fetchAppetizers(restaurantCode)

    override suspend fun fetchMenu(restaurantCode: String): ResultState<List<Product>> =
        productNetworkDataSource.fetchMenu(restaurantCode)

    override suspend fun find(code: String): ResultState<Product> =
        productNetworkDataSource.find(code)

    override suspend fun find(code: String, measure: Measure): ResultState<Product> =
        productNetworkDataSource.find(code, measure)

    override suspend fun hasStock(code: String): ResultState<Boolean> =
        productNetworkDataSource.hasStock(code)

    override suspend fun findSaleStrategy(code: String): ResultState<SaleProductStrategy> =
        productNetworkDataSource.findSaleStrategy(code)

}