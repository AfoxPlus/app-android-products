package com.afoxplus.products.delivery.views.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.afoxplus.products.databinding.FragmentProductsRecommendedBinding
import com.afoxplus.products.delivery.viewmodels.RecommendedProductsViewModel
import com.afoxplus.products.delivery.views.adapters.ProductRecommendedAdapter
import com.afoxplus.products.entities.Product
import com.afoxplus.uikit.fragments.BaseFragment

internal class RecommendedProductsFragment : BaseFragment() {

    private lateinit var fragmentRecommendedProductBinding: FragmentProductsRecommendedBinding
    private val recommendedProductsViewModel: RecommendedProductsViewModel by activityViewModels()
    private val productRecommendedAdapter: ProductRecommendedAdapter by lazy { ProductRecommendedAdapter() }

    override fun getMainView(inflater: LayoutInflater, container: ViewGroup?): View {
        fragmentRecommendedProductBinding = FragmentProductsRecommendedBinding.inflate(inflater)
        return fragmentRecommendedProductBinding.root
    }

    override fun setUpView() {
        fragmentRecommendedProductBinding.adapter = productRecommendedAdapter
        productRecommendedAdapter.setOnClickItemRecommendedProductListener(::onClickItemRecommendedProduct)
        recommendedProductsViewModel.fetchProductsRecommended()
    }

    override fun observerViewModel() {
        recommendedProductsViewModel.products.observe(viewLifecycleOwner) { products ->
            productRecommendedAdapter.submitList(products)
        }
    }

    private fun onClickItemRecommendedProduct(product: Product) {
        recommendedProductsViewModel.onClickItemRecommendedProduct(product)
    }
}