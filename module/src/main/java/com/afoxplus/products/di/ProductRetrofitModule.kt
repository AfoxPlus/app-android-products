package com.afoxplus.products.di

import android.content.Context
import android.os.Build
import com.afoxplus.uikit.extensions.convertToString
import com.afoxplus.uikit.service.BaseInterceptor
import com.afoxplus.uikit.service.annotations.MockService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.*
import okhttp3.ResponseBody.Companion.toResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Invocation
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named


@Module
@InstallIn(SingletonComponent::class)
internal class ProductRetrofitModule {

    companion object {
        const val PROVIDER_PRODUCTS_RETROFIT: String = "PROVIDER_PRODUCTS_RETROFIT"
        const val PROVIDER_PRODUCTS_INTERCEPTOR: String = "PROVIDER_PRODUCTS_INTERCEPTOR"
        const val PROVIDER_PRODUCTS_HTTP_CLIENT: String = "PROVIDER_PRODUCTS_HTTP_CLIENT"
        const val PROVIDER_PRODUCTS_URL: String = "PROVIDER_PRODUCTS_URL"
    }
    
    @Provides
    @Named(PROVIDER_PRODUCTS_INTERCEPTOR)
    fun provideInterceptor(
        @ApplicationContext appContext: Context
    ): Interceptor = BaseInterceptor(
        context = appContext
    ) { chain: Interceptor.Chain ->
        val request = chain.request()
        val invocation: Invocation? = request.tag(Invocation::class.java)
        invocation?.method()?.let { method ->
            val mockService = method.getAnnotation(MockService::class.java)
            //TODO: add condition && BuildConfig.DEBUG
            if (mockService != null && mockService.jsonFileName.isNotEmpty()) {
                return@BaseInterceptor setUpMockInterceptor(
                    mockService.jsonFileName,
                    appContext,
                    chain
                )
            } else return@BaseInterceptor setUpInterceptor(chain)
        } ?: return@BaseInterceptor setUpInterceptor(chain)
    }

    @Provides
    @Named(PROVIDER_PRODUCTS_RETROFIT)
    fun providerRetrofit(
        @Named(PROVIDER_PRODUCTS_URL)baseUrl: String,
        @Named(PROVIDER_PRODUCTS_HTTP_CLIENT) client: OkHttpClient,
        gsonConverterFactory: GsonConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Named(PROVIDER_PRODUCTS_HTTP_CLIENT)
    fun providerOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
        @Named(PROVIDER_PRODUCTS_INTERCEPTOR)apiInterceptor: Interceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(apiInterceptor)
            .build()
    }

    private fun setUpMockInterceptor(
        jsonFileName: String,
        context: Context,
        chain: Interceptor.Chain
    ): Response {
        val request = chain.request()
        return Response.Builder()
            .request(request)
            .protocol(Protocol.HTTP_1_1)
            .message("")
            .code(200)
            .body(getMockResponseBody(context, jsonFileName))
            .build()
    }

    private fun setUpInterceptor(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()
            .addHeader("device", "${Build.MANUFACTURER} ${Build.MODEL}")
            .build()
        return chain.proceed(requestBuilder)
    }

    private fun getMockResponseBody(context: Context, jsonFileName: String): ResponseBody? {
        val inputStream = context.assets.open(jsonFileName)
        return inputStream.convertToString()?.toResponseBody(BaseInterceptor.JSON_MEDIA_TYPE)
    }
}