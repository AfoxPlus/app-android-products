package com.afoxplus.products.entities

import com.afoxplus.products.entities.bussineslogic.OfferProductStrategy
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class ProductTest {

    @Mock
    private lateinit var offerProductStrategy: OfferProductStrategy

    @Mock
    private lateinit var productType: ProductType

    private lateinit var product: Product

    @Before
    fun setUp() {
        product = Product(
            code = "",
            name = "",
            description = "",
            imageUrl = "",
            measure = Measure("UNI", "Unidad"),
            currency = Currency("PEN", "S/"),
            stock = 10,
            productType = productType,
            saleStrategy = offerProductStrategy,
            price = 20.00
        )
    }

    @Test
    fun `should get price for sale when saleStrategy is not null`() {
        whenever(offerProductStrategy.calculateNewPrice(product)).thenReturn(15.00)
        assertThat(product.getPriceForSale()).isEqualTo(15.00)
        assertThat(product.getPriceForSaleWithFormat()).isEqualTo("S/ 15.00")
    }

    @Test
    fun `should get original price when saleStrategy is null`() {
        product.saleStrategy = null
        assertThat(product.getPriceForSale()).isEqualTo(20.00)
        assertThat(product.getPriceForSaleWithFormat()).isEqualTo("S/ 20.00")
        assertThat(product.getOriginalPrice()).isEqualTo(20.00)
        assertThat(product.getOriginalPriceWithFormat()).isEqualTo("S/ 20.00")
    }
}