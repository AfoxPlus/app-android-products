package com.afoxplus.products.di

import com.afoxplus.products.usecases.FetchProductsUseCase
import com.afoxplus.products.usecases.FindProductUseCase
import com.afoxplus.products.usecases.FindSaleProductStrategyUseCase
import com.afoxplus.products.usecases.HasProductStockUseCase
import com.afoxplus.products.usecases.actions.FetchProduct
import com.afoxplus.products.usecases.actions.FindProduct
import com.afoxplus.products.usecases.actions.FindSaleProductStrategy
import com.afoxplus.products.usecases.actions.HasProductStock
import com.afoxplus.products.usecases.repositories.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object ProductUseCaseModule {
    @Provides
    fun provideFetchProductsUseCase(productRepository: ProductRepository): FetchProduct =
        FetchProductsUseCase(productRepository)

    @Provides
    fun provideFindSaleProductStrategy(productRepository: ProductRepository): FindSaleProductStrategy =
        FindSaleProductStrategyUseCase(productRepository)

    @Provides
    fun provideFindProductUseCase(productRepository: ProductRepository): FindProduct =
        FindProductUseCase(productRepository)

    @Provides
    fun provideHasProductStock(): HasProductStock = HasProductStockUseCase()
}