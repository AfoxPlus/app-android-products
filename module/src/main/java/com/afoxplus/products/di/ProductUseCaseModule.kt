package com.afoxplus.products.di

import com.afoxplus.products.delivery.helpres.GetProductsStringsHelper
import com.afoxplus.products.delivery.helpres.GetProductsStringsHelperImpl
import com.afoxplus.products.usecases.*
import com.afoxplus.products.usecases.FetchAppetizerByCurrentRestaurantUseCase
import com.afoxplus.products.usecases.FetchProductByCurrentRestaurantUseCase
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
    fun bindFetchProductsUseCase(fetchProducts: FetchProductByCurrentRestaurantUseCase): FetchProductByCurrentRestaurant

    @Binds
    fun bindFindSaleProductStrategy(findSaleProductStrategy: FindSaleProductStrategyUseCase): FindSaleProductStrategy

    @Binds
    fun bindFindProductUseCase(findProduct: FindProductUseCase): FindProduct

    @Binds
    fun bindHasProductStock(hasProductStock: HasProductStockUseCase): HasProductStock

    @Binds
    fun bindFetchAppetizer(fetchAppetizer: FetchAppetizerByCurrentRestaurantUseCase): FetchAppetizerByCurrentRestaurant

    @Binds
    fun bindFetchHomeOffer(fetchHomeOffer: FetchHomeOfferUseCase): FetchHomeOffer

    @Binds
    fun bindFetchSaleOffer(fetchSaleOffer: FetchSaleOfferByCurrentRestaurantUseCase): FetchSaleOfferByCurrentRestaurant

    @Binds
    fun bindFetchMenu(fetchMenu: FetchMenuByCurrentRestaurantUseCase): FetchMenuByCurrentRestaurant

    @Binds
    fun GetProductsStringsHelper(getProductsStringsHelper: GetProductsStringsHelperImpl): GetProductsStringsHelper
}