package com.afoxplus.products.entities.bussineslogic

import android.os.Parcelable
import com.afoxplus.products.entities.Product

/**
 * SaleProductStrategy is a base interface for calculate new price from product
 */
interface SaleProductStrategy : Parcelable {
    var strategyCode: String
    fun calculateNewPrice(product: Product): Double
}