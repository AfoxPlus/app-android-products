package com.afoxplus.products.entities.bussineslogic.strategies

import com.afoxplus.products.entities.Currency
import com.afoxplus.products.entities.Measure
import com.afoxplus.products.entities.Product
import com.afoxplus.products.entities.ProductType
import com.google.common.truth.Truth.assertThat
import org.junit.Assert.assertThrows

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DiscountByOfferTest {

    @Mock
    private lateinit var productType: ProductType
    private lateinit var product: Product
    private lateinit var discountByOffer: DiscountByOffer

    @Before
    fun setUp() {
        discountByOffer = DiscountByOffer(
            strategyCode = "TEST",
            percentDiscount = 0.20,
            restaurantCode = "REST_TEST",
            restaurantName = "Rest. Test"
        )
        product = Product(
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
    }

    @Test
    fun `should calculate price with discount offer`() {
        assertThat(product.getPriceForSale()).isEqualTo(80)
        assertThat(product.getPriceForSaleWithFormat()).isEqualTo("S/ 80.00")
    }

    @Test
    fun `should throw error when percent is less than LIMIT_PERCENT_DISCOUNT`() {
        discountByOffer.percentDiscount = 0.05
        val ex: Exception = assertThrows(Exception::class.java) { product.getPriceForSale() }
        assertThat(ex).hasMessageThat().contains("Wrong percentage")
    }

    @Test
    fun `should throw error when percent iis greater than LIMIT_PERCENT_DISCOUNT`() {
        discountByOffer.percentDiscount = 0.81
        val ex: Exception = assertThrows(Exception::class.java) { product.getPriceForSale() }
        assertThat(ex).hasMessageThat().contains("Wrong percentage")
    }
}