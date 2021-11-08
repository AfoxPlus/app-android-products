package com.afoxplus.products.delivery.views.adapters.viewholders

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.afoxplus.products.databinding.ItemProductsMenuBinding
import com.afoxplus.products.delivery.models.ProductUIModel
import com.afoxplus.products.delivery.views.adapters.ProductAdapter
import com.bumptech.glide.Glide

internal class ProductMenuViewHolder private constructor(
    private val context: Context,
    private val productsMenuBinding: ItemProductsMenuBinding,
    private val onClickItem: ProductAdapter.OnClickProduct
) : ProductItemViewHolder(productsMenuBinding) {

    override fun bind(productUIModel: ProductUIModel) {
        super.bind(productUIModel)
        productsMenuBinding.product = productUIModel.product
        Glide.with(context).load(productUIModel.product.imageUrl)
            .into(productsMenuBinding.productMenuImage)
        productsMenuBinding.onClickItemRecommendedProduct = onClickItem
    }

    companion object {
        fun from(
            parent: ViewGroup,
            goToOrder: ProductAdapter.OnClickProduct
        ): ProductMenuViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemProductsMenuBinding.inflate(layoutInflater, parent, false)
            return ProductMenuViewHolder(parent.context, binding, goToOrder)
        }
    }
}