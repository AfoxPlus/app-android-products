package com.afoxplus.products.di

import com.afoxplus.products.repositories.sources.network.MeasureNetworkDataSource
import com.afoxplus.products.repositories.sources.network.ProductNetworkDataSource
import com.afoxplus.products.repositories.sources.network.api.MeasureApiNetwork
import com.afoxplus.products.repositories.sources.network.api.ProductApiNetwork
import com.afoxplus.products.repositories.sources.network.service.MeasureNetworkService
import com.afoxplus.products.repositories.sources.network.service.ProductNetworkService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object ProductNetworkDataSourceModule {
    @Provides
    fun provideMeasureNetworkDataSource(measureService: MeasureApiNetwork): MeasureNetworkDataSource =
        MeasureNetworkService(measureService)

    @Provides
    fun provideProductNetworkDataSource(productService: ProductApiNetwork): ProductNetworkDataSource =
        ProductNetworkService(productService)
}