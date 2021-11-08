package com.afoxplus.products.entities

import android.os.Parcelable
import com.afoxplus.products.entities.bussineslogic.OfferProductStrategy
import com.afoxplus.products.entities.bussineslogic.SaleProductStrategy
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val code: String,
    val name: String,
    var description: String,
    val imageUrl: String,
    val measure: Measure,
    val currency: Currency,
    var stock: Int,
    private var price: Double,
    var saleStrategy: SaleProductStrategy? = null
) : Parcelable {

    fun addSaleProductStrategy(saleStrategy: SaleProductStrategy) {
        this.saleStrategy = saleStrategy
    }

    /**
     * Only informative, Do not use for to do order
     */
    fun getOriginalPrice(): Double = this.price

    /**
     * Use for calculate in order
     */
    fun getPriceForSale(): Double {
        return if (saleStrategy == null) getOriginalPrice()
        else saleStrategy?.calculateNewPrice(this) ?: 0.00
    }

    fun getPriceForSaleWithFormat(): String =
        "${currency.value} ${String.format("%.2f", getPriceForSale())}"

    fun getOriginalPriceWithFormat(): String =
        "${currency.value} ${String.format("%.2f", getOriginalPrice())}"

    fun getOfferProductStrategy(): OfferProductStrategy? {
        return if (saleStrategy is OfferProductStrategy) {
            saleStrategy as OfferProductStrategy
        } else null
    }
}