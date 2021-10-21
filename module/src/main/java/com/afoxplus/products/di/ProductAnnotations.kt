package com.afoxplus.products.di

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ProductGsonConverterFactory

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ProductHttpLoggingInterceptor

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ProductInterceptor

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ProductRetrofit

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ProductOkHttpClient

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class ProductBaseURL