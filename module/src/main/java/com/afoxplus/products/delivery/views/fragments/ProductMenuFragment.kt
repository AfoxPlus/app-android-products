package com.afoxplus.products.delivery.views.fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import com.afoxplus.products.databinding.FragmentProductsMenuBinding
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

internal class ProductMenuFragment : UIKitBaseFragment() {
    private lateinit var fragmentMenuBinding: FragmentProductsMenuBinding
    private val productViewModel: ProductViewModel by activityViewModels()
    private val productAppetizerAdapter: ProductAdapter by lazy { ProductAdapter() }
    private val productMenuAdapter: ProductAdapter by lazy { ProductAdapter() }

    override fun getMainView(inflater: LayoutInflater, container: ViewGroup?): View {
        fragmentMenuBinding = FragmentProductsMenuBinding.inflate(inflater)
        return fragmentMenuBinding.root
    }

    override fun setUpView() {
        fragmentMenuBinding.layoutEmpty.container.setGone()
        fragmentMenuBinding.productMenuAdapter = productMenuAdapter
        fragmentMenuBinding.productAppetizerAdapter = productAppetizerAdapter
        productMenuAdapter.setOnClickProductListener(::onClickProductMenuEvent)
        productViewModel.fetchProductsAppetizer()
        productViewModel.fetchProductsMenu()
    }

    override fun observerViewModel() {
        productViewModel.productMenu.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ListSuccess -> productMenuAdapter.submitList(state.data)
                is ProductViewModel.EmptyProduct -> handleEmptyProductMenu(
                    getString(state.title),
                    getString(state.description)
                )
                is ListLoading -> showToast("Loading...")
                is ListError -> showToast("Internal Error")
            }
        }

        productViewModel.productAppetizer.observe(viewLifecycleOwner) { state ->

            when (state) {
                is ListSuccess -> productAppetizerAdapter.submitList(state.data)
                is ListEmptyData -> {
                    fragmentMenuBinding.titleAppetizer.setGone()
                    fragmentMenuBinding.recyclerProductAppetizer.setGone()
                }
                is ListLoading -> showToast("Loading...")
                is ListError -> showToast("Internal Error")
            }
        }
    }


    private fun handleEmptyProductMenu(title: String, description: String) {
        fragmentMenuBinding.titleMenu.setGone()
        fragmentMenuBinding.recyclerProductMenu.setGone()
        fragmentMenuBinding.layoutEmpty.lblDescription.text = description
        fragmentMenuBinding.layoutEmpty.lblTitle.text = title
        fragmentMenuBinding.layoutEmpty.container.setVisible()
    }

    private fun onClickProductMenuEvent(product: Product) {
        productViewModel.onClickProductEvent(product)
    }

    private fun showToast(msg: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(requireContext(), msg, duration).show()
    }
}