package com.afoxplus.products.di

import com.afoxplus.products.repositories.sources.network.api.MeasureApiNetwork
import com.afoxplus.products.repositories.sources.network.api.ProductApiNetwork
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ProductApiNetworkModule {

    @Provides
    @Singleton
    fun provideProductsUrl(): String = "http://127.0.0.1:3001/"

    @Provides
    fun providerMeasureService(
        @Named(ProductRetrofitModule.PROVIDER_PRODUCTS_RETROFIT) retrofit: Retrofit
    ): MeasureApiNetwork = retrofit.create(MeasureApiNetwork::class.java)

    @Provides
    fun providerProductService(
        @Named(ProductRetrofitModule.PROVIDER_PRODUCTS_RETROFIT) retrofit: Retrofit
    ): ProductApiNetwork = retrofit.create(ProductApiNetwork::class.java)

}