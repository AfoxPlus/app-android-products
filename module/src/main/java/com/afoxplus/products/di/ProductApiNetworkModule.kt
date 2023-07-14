package com.afoxplus.products.di

import com.afoxplus.network.api.RetrofitGenerator
import com.afoxplus.products.repositories.sources.network.api.MeasureApiNetwork
import com.afoxplus.products.repositories.sources.network.api.ProductApiNetwork
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object ProductApiNetworkModule {
    @Provides
    fun providerMeasureService(
        retrofitGenerator: RetrofitGenerator
    ): MeasureApiNetwork = retrofitGenerator.createRetrofit(MeasureApiNetwork::class.java)

    @Provides
    fun providerProductService(
        retrofitGenerator: RetrofitGenerator
    ): ProductApiNetwork = retrofitGenerator.createRetrofit(ProductApiNetwork::class.java)

}