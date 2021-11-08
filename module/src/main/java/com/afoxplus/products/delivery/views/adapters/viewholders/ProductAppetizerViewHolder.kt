package com.afoxplus.products.delivery.views.adapters.viewholders

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.afoxplus.products.databinding.ItemProductsAppetizerBinding
import com.afoxplus.products.delivery.models.ProductUIModel
import com.bumptech.glide.Glide

internal class ProductAppetizerViewHolder private constructor(
    private val context: Context,
    private val productAppetizerBinding: ItemProductsAppetizerBinding
) : ProductItemViewHolder(productAppetizerBinding) {
    override fun bind(productUIModel: ProductUIModel) {
        super.bind(productUIModel)
        productAppetizerBinding.product = productUIModel.product
        Glide.with(context).load(productUIModel.product.imageUrl)
            .into(productAppetizerBinding.appetizerImage)
    }

    companion object {
        fun from(
            parent: ViewGroup
        ): ProductAppetizerViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemProductsAppetizerBinding.inflate(layoutInflater, parent, false)
            return ProductAppetizerViewHolder(parent.context, binding)
        }
    }
}