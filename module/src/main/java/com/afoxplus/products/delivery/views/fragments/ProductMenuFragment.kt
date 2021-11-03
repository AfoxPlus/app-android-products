package com.afoxplus.products.delivery.views.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.afoxplus.products.databinding.FragmentProductsMenuBinding
import com.afoxplus.products.delivery.viewmodels.ProductViewModel
import com.afoxplus.products.delivery.views.adapters.ProductAdapter
import com.afoxplus.products.entities.Product
import com.afoxplus.uikit.fragments.BaseFragment

internal class ProductMenuFragment : BaseFragment() {
    private lateinit var fragmentMenuBinding: FragmentProductsMenuBinding
    private val productViewModel: ProductViewModel by activityViewModels()
    private val productAppetizerAdapter: ProductAdapter by lazy { ProductAdapter() }
    private val productMenuAdapter: ProductAdapter by lazy { ProductAdapter() }

    override fun getMainView(inflater: LayoutInflater, container: ViewGroup?): View {
        fragmentMenuBinding = FragmentProductsMenuBinding.inflate(inflater)
        return fragmentMenuBinding.root
    }

    override fun setUpView() {
        fragmentMenuBinding.productMenuAdapter = productMenuAdapter
        fragmentMenuBinding.productAppetizerAdapter = productAppetizerAdapter
        productMenuAdapter.setOnClickProductListener(::onClickProductMenuEvent)
        productViewModel.fetchProductsAppetizer()
        productViewModel.fetchProductsMenu()
    }

    override fun observerViewModel() {
        productViewModel.productMenu.observe(viewLifecycleOwner) { products ->
            productMenuAdapter.submitList(products)
        }
        productViewModel.productAppetizer.observe(viewLifecycleOwner) { products ->
            productAppetizerAdapter.submitList(products)
        }
    }

    private fun onClickProductMenuEvent(product: Product) {
        productViewModel.onClickProductEvent(product)
    }
}