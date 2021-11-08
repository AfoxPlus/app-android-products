package com.afoxplus.products.delivery.views.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.afoxplus.products.databinding.FragmentProductsHomeOfferBinding
import com.afoxplus.products.delivery.viewmodels.ProductViewModel
import com.afoxplus.products.delivery.views.adapters.ProductAdapter
import com.afoxplus.products.entities.Product
import com.afoxplus.uikit.fragments.BaseFragment

internal class ProductHomeOfferFragment : BaseFragment() {
    private lateinit var fragmentHomeOfferBinding: FragmentProductsHomeOfferBinding
    private val productViewModel: ProductViewModel by activityViewModels()
    private val productHomeOfferAdapter: ProductAdapter by lazy { ProductAdapter() }

    override fun getMainView(inflater: LayoutInflater, container: ViewGroup?): View {
        fragmentHomeOfferBinding = FragmentProductsHomeOfferBinding.inflate(inflater)
        return fragmentHomeOfferBinding.root
    }

    override fun setUpView() {
        fragmentHomeOfferBinding.productHomeOfferAdapter = productHomeOfferAdapter
        productHomeOfferAdapter.setOnClickProductListener(::onClickProductEvent)
        productViewModel.fetchProductsHomeOffer()
    }

    override fun observerViewModel() {
        productViewModel.productsHomeOffer.observe(viewLifecycleOwner) { products ->
            productHomeOfferAdapter.submitList(products)
        }
    }

    private fun onClickProductEvent(product: Product) {
        productViewModel.onClickProductOfferEvent(product)
    }
}