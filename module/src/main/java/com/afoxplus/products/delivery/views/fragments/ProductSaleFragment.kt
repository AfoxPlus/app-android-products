package com.afoxplus.products.delivery.views.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.afoxplus.products.databinding.FragmentProductsSaleBinding
import com.afoxplus.products.delivery.models.ProductUIModel
import com.afoxplus.products.delivery.viewmodels.ProductViewModel
import com.afoxplus.products.delivery.views.adapters.ProductAdapter
import com.afoxplus.products.entities.Product
import com.afoxplus.uikit.extensions.setGone
import com.afoxplus.uikit.extensions.setVisible
import com.afoxplus.uikit.fragments.UIKitBaseFragment
import com.afoxplus.uikit.views.status.ListEmptyData
import com.afoxplus.uikit.views.status.ListError
import com.afoxplus.uikit.views.status.ListLoading
import com.afoxplus.uikit.views.status.ListSuccess

internal class ProductSaleFragment : UIKitBaseFragment() {

    private lateinit var binding: FragmentProductsSaleBinding
    private val productViewModel: ProductViewModel by activityViewModels()
    private val productSaleAdapter: ProductAdapter by lazy { ProductAdapter() }
    private val productOfferAdapter: ProductAdapter by lazy { ProductAdapter() }

    override fun getMainView(inflater: LayoutInflater, container: ViewGroup?): View {
        binding = FragmentProductsSaleBinding.inflate(inflater)
        return binding.root
    }

    override fun setUpView() {
        binding.layoutEmpty.container.setGone()
        setupRecyclerOffer()
        setupRecyclerProduct()
        productSaleAdapter.setOnClickProductListener(::onClickProductEvent)
        productOfferAdapter.setOnClickProductListener(::onClickProductEvent)
        productViewModel.fetchProductOffers()
        productViewModel.fetchProductSales()
    }

    override fun observerViewModel() {
        productViewModel.productsSale.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ListSuccess ->
                    productSaleAdapter.submitList(state.data)
                is ProductViewModel.EmptyProduct -> handleEmptyProductSale(
                    getString(state.title),
                    getString(state.description)
                )
                is ListLoading -> onListLoading()
                is ListError -> onListError()
            }
        }

        productViewModel.productOffer.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ListSuccess -> setDataProductOfferAdapter(state.data)
                is ListEmptyData -> binding.offerContent.visibility = View.GONE
                is ListLoading -> onListLoading()
                is ListError -> onListError()
            }
        }
    }

    private fun handleEmptyProductSale(title: String, description: String) {
        binding.offerContent.setGone()
        binding.layoutEmpty.lblTitle.text = title
        binding.layoutEmpty.lblDescription.text = description
        binding.layoutEmpty.container.setVisible()
    }

    private fun setDataProductOfferAdapter(data: List<ProductUIModel>) {
        binding.offerContent.visibility = View.VISIBLE
        productOfferAdapter.submitList(data)
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

    private fun onListLoading() {
        //Nothing
    }

    private fun onListError() {
        //Nothing
    }
}