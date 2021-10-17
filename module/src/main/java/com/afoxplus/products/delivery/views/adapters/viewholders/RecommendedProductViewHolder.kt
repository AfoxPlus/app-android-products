package com.afoxplus.products.delivery.views.adapters.viewholders

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.afoxplus.products.databinding.ItemProductsRecommendedBinding
import com.afoxplus.products.delivery.views.adapters.ProductRecommendedAdapter
import com.afoxplus.products.entities.Product
import com.bumptech.glide.Glide

internal class RecommendedProductViewHolder private constructor(
    private val context: Context,
    private val productMenuBinding: ItemProductsRecommendedBinding,
    private val onClickItemRecommended: ProductRecommendedAdapter.OnClickItemRecommendedProduct
) :
    RecyclerView.ViewHolder(productMenuBinding.root) {

    fun bind(product: Product) {
        productMenuBinding.product = product
        Glide.with(context).load(product.imageUrl).into(productMenuBinding.productImage)
        productMenuBinding.onClickItemRecommendedProduct = onClickItemRecommended
        productMenuBinding.executePendingBindings()
    }

    companion object {
        fun from(
            parent: ViewGroup,
            goToOrder: ProductRecommendedAdapter.OnClickItemRecommendedProduct
        ): RecommendedProductViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemProductsRecommendedBinding.inflate(layoutInflater, parent, false)
            return RecommendedProductViewHolder(parent.context, binding, goToOrder)
        }
    }

}