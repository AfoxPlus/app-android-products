package com.afoxplus.products.delivery.views.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.afoxplus.products.delivery.views.adapters.viewholders.RecommendedProductViewHolder
import com.afoxplus.products.entities.Product

internal class ProductRecommendedAdapter :
    ListAdapter<Product, RecommendedProductViewHolder>(ProductMenuDiffUtilCallback()) {

    private var itemRecommendedProduct: OnClickItemRecommendedProduct =
        OnClickItemRecommendedProduct {}

    fun setOnClickItemRecommendedProductListener(listener: OnClickItemRecommendedProduct) {
        itemRecommendedProduct = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecommendedProductViewHolder =
        RecommendedProductViewHolder.from(parent, itemRecommendedProduct)

    override fun onBindViewHolder(holderRecommended: RecommendedProductViewHolder, position: Int) =
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