package com.afoxplus.products.di

import com.afoxplus.products.delivery.flow.ProductFlow
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
object ProductFlowModule {
    @Provides
    fun bindProductFlow(): ProductFlow = ProductFlow.build()
}