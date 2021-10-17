package com.afoxplus.products.di

import com.afoxplus.products.repositories.MeasureRepositorySource
import com.afoxplus.products.repositories.ProductRepositorySource
import com.afoxplus.products.repositories.sources.network.MeasureNetworkDataSource
import com.afoxplus.products.repositories.sources.network.ProductNetworkDataSource
import com.afoxplus.products.usecases.repositories.MeasureRepository
import com.afoxplus.products.usecases.repositories.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object ProductRepositoryModule {
    @Provides
    fun provideProductRepository(
        productNetworkDataSource: ProductNetworkDataSource
    ): ProductRepository = ProductRepositorySource(productNetworkDataSource)

    @Provides
    fun provideMeasureRepository(measureNetworkDataSource: MeasureNetworkDataSource): MeasureRepository =
        MeasureRepositorySource(measureNetworkDataSource)
}