package com.afoxplus.products.delivery.helpres

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
            lblTitle = "¡Los sentimos!",
            lblDescription = "No contamos con platos a la carta por el momento"
        )
    }

    override fun getMenuEmptyStringsUIModel(): EmptyStringsUIModel {
        return EmptyStringsUIModel(
            lblTitle = "¡Los sentimos!",
            lblDescription = "No contamos con menu por el momento"
        )
    }

}