package com.afoxplus.products.delivery.views.adapters.viewholders

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.afoxplus.products.databinding.ItemProductsOfferBinding
import com.afoxplus.products.delivery.models.ProductUIModel
import com.afoxplus.products.delivery.views.adapters.ProductAdapter
import com.bumptech.glide.Glide

internal class ProductOfferViewHolder private constructor(
    private val context: Context,
    private val productOfferBinding: ItemProductsOfferBinding,
    private val onClickItem: ProductAdapter.OnClickProduct
) :
    ProductItemViewHolder(productOfferBinding) {

    override fun bind(productUIModel: ProductUIModel) {
        super.bind(productUIModel)
        productOfferBinding.product = productUIModel.product
        Glide.with(context).load(productUIModel.product.imageUrl)
            .into(productOfferBinding.imageOffer)
        productOfferBinding.onClickProductOffer = onClickItem
    }

    companion object {
        fun from(
            parent: ViewGroup,
            goToOrder: ProductAdapter.OnClickProduct
        ): ProductOfferViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemProductsOfferBinding.inflate(layoutInflater, parent, false)
            return ProductOfferViewHolder(parent.context, binding, goToOrder)
        }
    }
}