package com.afoxplus.products.entities.bussineslogic

interface OfferProductStrategy : SaleProductStrategy {
    companion object {
        const val LIMIT_PERCENT_DISCOUNT: Double = 0.80
        const val MINIMUM_PERCENT_DISCOUNT: Double = 0.05
    }

    var percentDiscount: Double
    var marketName: String
}