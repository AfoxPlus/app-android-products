package com.afoxplus.products.di

import com.afoxplus.products.delivery.helpres.GetProductsStringsHelper
import com.afoxplus.products.delivery.helpres.GetProductsStringsHelperImpl
import com.afoxplus.products.usecases.*
import com.afoxplus.products.usecases.FetchAppetizerUseCase
import com.afoxplus.products.usecases.FetchProductsUseCase
import com.afoxplus.products.usecases.FindProductUseCase
import com.afoxplus.products.usecases.FindSaleProductStrategyUseCase
import com.afoxplus.products.usecases.HasProductStockUseCase
import com.afoxplus.products.usecases.actions.*
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface ProductUseCaseModule {
    @Binds
    fun bindFetchProductsUseCase(fetchProducts: FetchProductsUseCase): FetchProduct

    @Binds
    fun bindFindSaleProductStrategy(findSaleProductStrategy: FindSaleProductStrategyUseCase): FindSaleProductStrategy

    @Binds
    fun bindFindProductUseCase(findProduct: FindProductUseCase): FindProduct

    @Binds
    fun bindHasProductStock(hasProductStock: HasProductStockUseCase): HasProductStock

    @Binds
    fun bindFetchAppetizer(fetchAppetizer: FetchAppetizerUseCase): FetchAppetizer

    @Binds
    fun bindFetchHomeOffer(fetchHomeOffer: FetchHomeOfferUseCase): FetchHomeOffer

    @Binds
    fun bindFetchSaleOffer(fetchSaleOffer: FetchSaleOfferUseCase): FetchSaleOffer

    @Binds
    fun bindFetchMenu(fetchMenu: FetchMenuUseCase): FetchMenu

    @Binds
    fun GetProductsStringsHelper(getProductsStringsHelper: GetProductsStringsHelperImpl): GetProductsStringsHelper
}