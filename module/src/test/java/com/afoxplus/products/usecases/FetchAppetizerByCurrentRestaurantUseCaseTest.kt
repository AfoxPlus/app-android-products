package com.afoxplus.products.usecases

import com.afoxplus.products.entities.Currency
import com.afoxplus.products.entities.Measure
import com.afoxplus.products.entities.Product
import com.afoxplus.products.entities.ProductType
import com.afoxplus.products.entities.bussineslogic.strategies.DiscountByOffer
import com.afoxplus.products.usecases.repositories.ProductRepository
import com.afoxplus.uikit.objects.vendor.Vendor
import com.afoxplus.uikit.objects.vendor.VendorShared
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.any
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class FetchAppetizerByCurrentRestaurantUseCaseTest {

    @Mock
    private lateinit var productRepository: ProductRepository

    @Mock
    private lateinit var shared: VendorShared

    @Mock
    private lateinit var productType: ProductType
    private lateinit var discountByOffer: DiscountByOffer
    private lateinit var fetchAppetizerUseCase: FetchAppetizerByCurrentRestaurantUseCase
    private lateinit var listAppetizer: List<Product>

    @Before
    fun setUp() {
        fetchAppetizerUseCase = FetchAppetizerByCurrentRestaurantUseCase(productRepository,shared)
        discountByOffer = DiscountByOffer(
            strategyCode = "TEST",
            percentDiscount = 0.20,
            restaurantCode = "REST_TEST",
            restaurantName = "Rest. Test"
        )
        listAppetizer = listOf(
            Product(
                code = "",
                name = "",
                description = "",
                imageUrl = "",
                measure = Measure("UNI", "Unidad"),
                currency = Currency("PEN", "S/"),
                stock = 10,
                productType = productType,
                saleStrategy = discountByOffer,
                price = 100.00
            )
        )
    }

    @Test
    fun `should call fetchAppetizers`() {
        runBlocking {
            val vendor = Vendor(tableId ="01", restaurantId = "01")
            whenever(shared.fetch()).thenReturn(vendor)
            fetchAppetizerUseCase()
            verify(productRepository).fetchAppetizers(any())
        }
    }


}