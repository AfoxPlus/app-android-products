package com.afoxplus.products.repositories.sources.network.service

import com.afoxplus.network.response.BaseResponse
import com.afoxplus.products.repositories.sources.network.api.ProductApiNetwork
import com.afoxplus.products.repositories.sources.network.api.response.CurrencyResponse
import com.afoxplus.products.repositories.sources.network.api.response.MeasureResponse
import com.afoxplus.products.repositories.sources.network.api.response.ProductResponse
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class ProductNetworkServiceTest {

    @Mock
    private lateinit var productApiNetwork: ProductApiNetwork

    private lateinit var productNetworkService: ProductNetworkService

    @Before
    fun setUp() {
        productNetworkService = ProductNetworkService(productApiNetwork)
    }

    @Test
    fun `should call fetchSaleOffers`() {
        runBlocking {
            val headerMap = mapOf("restaurant_code" to "61a19c440b6de1476436de4a")
            whenever(productApiNetwork.fetchSaleOffers(headerMap)).thenReturn(
                Response.success(
                    BaseResponse(
                        true, "Ok", listOf(
                            ProductResponse(
                                code = "PROD",
                                name = "Plato",
                                description = "",
                                imageUrl = "",
                                stock = 0,
                                price = 20.5,
                                measure = MeasureResponse(
                                    code = "UNI",
                                    value = "Unidad"
                                ),
                                currency = CurrencyResponse(code = "PEN", value = "S/")
                            )
                        )
                    )
                )
            )
            productNetworkService.fetchSaleOffers()
            verify(productApiNetwork).fetchSaleOffers(headerMap)
        }
    }

}