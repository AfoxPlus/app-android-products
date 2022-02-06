package com.afoxplus.products.delivery.views.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.afoxplus.products.databinding.FragmentProductsSaleBinding
import com.afoxplus.products.delivery.viewmodels.ProductViewModel
import com.afoxplus.products.delivery.views.adapters.ProductAdapter
import com.afoxplus.products.entities.Product
import com.afoxplus.uikit.fragments.BaseFragment

internal class ProductSaleFragment : BaseFragment() {

    private lateinit var binding: FragmentProductsSaleBinding

    private val productViewModel: ProductViewModel by activityViewModels()
    private val productSaleAdapter: ProductAdapter by lazy { ProductAdapter() }
    private val productOfferAdapter: ProductAdapter by lazy { ProductAdapter() }

    override fun getMainView(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = FragmentProductsSaleBinding.inflate(inflater)
        return binding.root
    }

    override fun setUpView() {
        setupRecyclerOffer()
        setupRecyclerProduct()
        productSaleAdapter.setOnClickProductListener(::onClickProductEvent)
        productOfferAdapter.setOnClickProductListener(::onClickProductEvent)
        productViewModel.fetchProductOffers()
        productViewModel.fetchProductSales()
    }

    override fun observerViewModel() {
        productViewModel.productsSale.observe(viewLifecycleOwner) { products ->
            productSaleAdapter.submitList(products)
        }
        productViewModel.productOffer.observe(viewLifecycleOwner) { products ->
            productOfferAdapter.submitList(products)
        }
    }

    private fun onClickProductEvent(product: Product) {
        productViewModel.onClickProductEvent(product)
    }

    private fun setupRecyclerOffer() {
        binding.recyclerOffers.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerOffers.adapter = productOfferAdapter
    }

    private fun setupRecyclerProduct() {
        binding.recyclerProducts.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerProducts.adapter = productSaleAdapter
    }
}