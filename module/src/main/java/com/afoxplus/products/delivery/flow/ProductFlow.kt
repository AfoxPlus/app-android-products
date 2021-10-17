package com.afoxplus.products.delivery.flow

import com.afoxplus.products.delivery.views.fragments.RecommendedProductsFragment
import com.afoxplus.uikit.fragments.BaseFragment
import javax.inject.Inject

interface ProductFlow {
    companion object {
        fun build(): ProductFlow = ProductFlowImpl()
    }

    fun getFragmentRecommendedProducts(): BaseFragment

}

internal class ProductFlowImpl @Inject constructor() : ProductFlow {
    override fun getFragmentRecommendedProducts(): BaseFragment = RecommendedProductsFragment()
}