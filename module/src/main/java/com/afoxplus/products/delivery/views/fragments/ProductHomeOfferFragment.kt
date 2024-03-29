package com.afoxplus.products.delivery.views.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.afoxplus.products.databinding.FragmentProductsHomeOfferBinding
import com.afoxplus.products.delivery.viewmodels.ProductViewModel
import com.afoxplus.products.delivery.views.adapters.ProductAdapter
import com.afoxplus.products.entities.Product
import com.afoxplus.uikit.extensions.setGone
import com.afoxplus.uikit.fragments.UIKitBaseFragment
import com.afoxplus.uikit.views.status.ListEmptyData
import com.afoxplus.uikit.views.status.ListError
import com.afoxplus.uikit.views.status.ListLoading
import com.afoxplus.uikit.views.status.ListSuccess

internal class ProductHomeOfferFragment : UIKitBaseFragment() {
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
        productViewModel.productsHomeOffer.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ListSuccess -> productHomeOfferAdapter.submitList(state.data)
                is ListEmptyData -> fragmentHomeOfferBinding.recyclerProductMenu.setGone()
                is ListLoading -> onListLoading()
                is ListError -> onListError()
            }
        }
    }

    private fun onClickProductEvent(product: Product) {
        productViewModel.onClickProductOfferEvent(product)
    }

    private fun onListLoading() {
        //Nothing
    }

    private fun onListError() {
        //Nothing
    }
}