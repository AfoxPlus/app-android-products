package com.afoxplus.products.delivery.views.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.afoxplus.products.databinding.FragmentProductsHomeOfferBinding
import com.afoxplus.products.delivery.viewmodels.ProductViewModel
import com.afoxplus.products.delivery.views.adapters.ProductAdapter
import com.afoxplus.products.entities.Product
import com.afoxplus.uikit.extensions.setGone
import com.afoxplus.uikit.fragments.BaseFragment
import com.afoxplus.uikit.views.status.ListEmptyData
import com.afoxplus.uikit.views.status.ListError
import com.afoxplus.uikit.views.status.ListLoading
import com.afoxplus.uikit.views.status.ListSuccess

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
        productViewModel.productsHomeOffer.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ListSuccess -> productHomeOfferAdapter.submitList(state.data)
                is ListEmptyData -> fragmentHomeOfferBinding.recyclerProductMenu.setGone()
                is ListLoading -> showToast("Loading...")
                is ListError -> showToast("Internal Error")
            }
        }
    }

    private fun onClickProductEvent(product: Product) {
        productViewModel.onClickProductOfferEvent(product)
    }

    private fun showToast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(requireContext(), msg, duration).show()
    }
}