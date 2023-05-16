package com.afoxplus.products.delivery.views.adapters.viewholders

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.afoxplus.products.databinding.ItemProductsSaleBinding
import com.afoxplus.products.delivery.models.ProductUIModel
import com.afoxplus.products.delivery.views.adapters.ProductAdapter
import com.bumptech.glide.Glide

internal class ProductSaleViewHolder private constructor(
    private val context: Context,
    private val productSaleBinding: ItemProductsSaleBinding,
    private val onClickItem: ProductAdapter.OnClickProduct
) : ProductItemViewHolder(productSaleBinding) {

    override fun bind(productUIModel: ProductUIModel) {
        super.bind(productUIModel)
        productSaleBinding.product = productUIModel.product
        Glide.with(context).load(productUIModel.product.imageUrl)
            .into(productSaleBinding.productImage)
        productSaleBinding.onClickItemRecommendedProduct = onClickItem
    }

    companion object {
        fun from(
            parent: ViewGroup,
            goToOrder: ProductAdapter.OnClickProduct
        ): ProductSaleViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemProductsSaleBinding.inflate(layoutInflater, parent, false)
            return ProductSaleViewHolder(parent.context, binding, goToOrder)
        }
    }
}