package com.afoxplus.products.di

import com.afoxplus.products.repositories.sources.network.api.MeasureApiNetwork
import com.afoxplus.products.repositories.sources.network.api.ProductApiNetwork
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
internal object ProductApiNetworkModule {
    @Provides
    fun providerMeasureService(
        @ProductRetrofit retrofit: Retrofit
    ): MeasureApiNetwork = retrofit.create(MeasureApiNetwork::class.java)

    @Provides
    fun providerProductService(
        @ProductRetrofit retrofit: Retrofit
    ): ProductApiNetwork = retrofit.create(ProductApiNetwork::class.java)

}