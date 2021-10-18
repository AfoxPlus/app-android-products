package com.afoxplus.products.di

import com.afoxplus.products.di.ProductRetrofitModule.Companion.PROVIDER_PRODUCTS_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ProductsModule {
    @Provides
    @Singleton
    @Named(PROVIDER_PRODUCTS_URL)
    fun provideBaseUrl(): String = "http://127.0.0.1:3001/"
}