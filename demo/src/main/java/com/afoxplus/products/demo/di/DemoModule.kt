package com.afoxplus.products.demo.di

import com.afoxplus.demo_config.delivery.flow.StartDemoFlow
import com.afoxplus.products.demo.global.ProductStartDemoFlow
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface DemoModule {

    @Binds
    fun buildAppProperties(demo: ProductStartDemoFlow): StartDemoFlow
}