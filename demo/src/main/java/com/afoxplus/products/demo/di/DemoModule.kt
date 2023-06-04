package com.afoxplus.products.demo.di

import com.afoxplus.network.global.AppProperties
import com.afoxplus.products.demo.global.AppPropertiesDemo
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DemoModule {

    @Binds
    fun buildAppProperties(demo: AppPropertiesDemo): AppProperties
}