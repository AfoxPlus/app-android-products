package com.afoxplus.products.delivery.flow

import com.afoxplus.products.delivery.views.fragments.ProductHomeOfferFragment
import com.afoxplus.products.delivery.views.fragments.ProductMenuFragment
import com.afoxplus.products.delivery.views.fragments.ProductSaleFragment
import com.afoxplus.uikit.fragments.UIKitBaseFragment
import javax.inject.Inject

interface ProductFlow {
    companion object {
        fun build(): ProductFlow = ProductFlowImpl()
    }

    fun getProductsSaleFragment(): UIKitBaseFragment
    fun getProductMenuFragment(): UIKitBaseFragment
    fun getProductHomeOfferFragment(): UIKitBaseFragment
}

internal class ProductFlowImpl @Inject constructor() : ProductFlow {
    override fun getProductsSaleFragment(): UIKitBaseFragment = ProductSaleFragment()
    override fun getProductMenuFragment(): UIKitBaseFragment = ProductMenuFragment()
    override fun getProductHomeOfferFragment(): UIKitBaseFragment = ProductHomeOfferFragment()
}