package com.afoxplus.products.delivery.views.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.afoxplus.products.databinding.FragmentProductsSaleBinding
import com.afoxplus.products.delivery.viewmodels.RecommendedProductsViewModel
import com.afoxplus.products.delivery.views.adapters.ProductSaleAdapter
import com.afoxplus.products.entities.Product
import com.afoxplus.uikit.fragments.BaseFragment

internal class ProductSaleFragment : BaseFragment() {

    private lateinit var fragmentRecommendedProductBinding: FragmentProductsSaleBinding
    private val recommendedProductsViewModel: RecommendedProductsViewModel by activityViewModels()
    private val productSaleAdapter: ProductSaleAdapter by lazy { ProductSaleAdapter() }

    override fun getMainView(inflater: LayoutInflater, container: ViewGroup?): View {
        fragmentRecommendedProductBinding = FragmentProductsSaleBinding.inflate(inflater)
        return fragmentRecommendedProductBinding.root
    }

    override fun setUpView() {
        fragmentRecommendedProductBinding.adapter = productSaleAdapter
        productSaleAdapter.setOnClickItemRecommendedProductListener(::onClickItemRecommendedProduct)
        recommendedProductsViewModel.fetchProductsRecommended()
    }

    override fun observerViewModel() {
        recommendedProductsViewModel.products.observe(viewLifecycleOwner) { products ->
            productSaleAdapter.submitList(products)
        }
    }

    private fun onClickItemRecommendedProduct(product: Product) {
        recommendedProductsViewModel.onClickItemRecommendedProduct(product)
    }
}