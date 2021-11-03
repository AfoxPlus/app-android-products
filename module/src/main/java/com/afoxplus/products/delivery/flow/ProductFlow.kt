package com.afoxplus.products.delivery.flow

import com.afoxplus.products.delivery.views.fragments.ProductMenuFragment
import com.afoxplus.products.delivery.views.fragments.ProductSaleFragment
import com.afoxplus.uikit.fragments.BaseFragment
import javax.inject.Inject

interface ProductFlow {
    companion object {
        fun build(): ProductFlow = ProductFlowImpl()
    }

    fun getProductsSaleFragment(): BaseFragment
    fun getProductMenuFragment(): BaseFragment
}

internal class ProductFlowImpl @Inject constructor() : ProductFlow {
    override fun getProductsSaleFragment(): BaseFragment = ProductSaleFragment()
    override fun getProductMenuFragment(): BaseFragment = ProductMenuFragment()
}