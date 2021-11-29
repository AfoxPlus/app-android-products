package com.afoxplus.products.entities.bussineslogic.strategies

import com.afoxplus.products.entities.Product
import com.afoxplus.products.entities.bussineslogic.OfferProductStrategy
import com.afoxplus.products.entities.bussineslogic.OfferProductStrategy.Companion.LIMIT_PERCENT_DISCOUNT
import com.afoxplus.products.entities.bussineslogic.OfferProductStrategy.Companion.MINIMUM_PERCENT_DISCOUNT
import kotlinx.parcelize.Parcelize

@Parcelize
internal class DiscountByOffer(
    override var strategyCode: String,
    override var percentDiscount: Double,
    override var restaurantCode: String,
    override var restaurantName: String
) : OfferProductStrategy {
    override fun calculateNewPrice(product: Product): Double {
        return if (percentDiscount > MINIMUM_PERCENT_DISCOUNT && percentDiscount <= LIMIT_PERCENT_DISCOUNT)
            product.getOriginalPrice() - (product.getOriginalPrice() * percentDiscount)
        else throw Exception("Wrong percentage")
    }

    companion object {
        const val STRATEGY_CODE_BLACK_FRIDAY: String = "SCBF"
    }
}