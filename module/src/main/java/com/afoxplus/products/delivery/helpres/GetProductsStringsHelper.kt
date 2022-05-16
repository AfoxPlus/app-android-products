package com.afoxplus.products.delivery.helpres

import com.afoxplus.products.R
import com.afoxplus.products.delivery.models.EmptyStringsUIModel
import javax.inject.Inject
import javax.inject.Singleton

internal interface GetProductsStringsHelper {
    fun getSalesEmptyStringsUIModel(): EmptyStringsUIModel
    fun getMenuEmptyStringsUIModel(): EmptyStringsUIModel
}

@Singleton
internal class GetProductsStringsHelperImpl @Inject constructor() :
    GetProductsStringsHelper {
    override fun getSalesEmptyStringsUIModel(): EmptyStringsUIModel {
        return EmptyStringsUIModel(
            lblTitle = R.string.products_empty_title,
            lblDescription = R.string.products_empty_sales_description
        )
    }

    override fun getMenuEmptyStringsUIModel(): EmptyStringsUIModel {
        return EmptyStringsUIModel(
            lblTitle = R.string.products_empty_title,
            lblDescription = R.string.products_empty_menu_description
        )
    }

}