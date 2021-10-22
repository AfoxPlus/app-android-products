package com.afoxplus.products.delivery.views.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.afoxplus.products.delivery.views.adapters.viewholders.ProductSaleViewHolder
import com.afoxplus.products.entities.Product

internal class ProductSaleAdapter :
    ListAdapter<Product, ProductSaleViewHolder>(ProductMenuDiffUtilCallback()) {

    private var itemRecommendedProduct: OnClickItemRecommendedProduct =
        OnClickItemRecommendedProduct {}

    fun setOnClickItemRecommendedProductListener(listener: OnClickItemRecommendedProduct) {
        itemRecommendedProduct = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductSaleViewHolder =
        ProductSaleViewHolder.from(parent, itemRecommendedProduct)

    override fun onBindViewHolder(holderRecommended: ProductSaleViewHolder, position: Int) =
        holderRecommended.bind(getItem(position))

    class ProductMenuDiffUtilCallback : DiffUtil.ItemCallback<Product>() {
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean =
            oldItem.code == newItem.code

        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean =
            oldItem == newItem
    }

    fun interface OnClickItemRecommendedProduct {
        fun invoke(product: Product)
    }
}