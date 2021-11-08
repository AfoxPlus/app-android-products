package com.afoxplus.products.delivery.views.adapters.viewholders

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.afoxplus.products.databinding.ItemProductsHomeOfferBinding
import com.afoxplus.products.delivery.models.ProductUIModel
import com.afoxplus.products.delivery.views.adapters.ProductAdapter
import com.bumptech.glide.Glide

internal class ProductHomeOfferViewHolder private constructor(
    private val context: Context,
    private val productHomeOfferBinding: ItemProductsHomeOfferBinding,
    private val onClickItem: ProductAdapter.OnClickProduct
) : ProductItemViewHolder(productHomeOfferBinding) {

    override fun bind(productUIModel: ProductUIModel) {
        super.bind(productUIModel)
        productHomeOfferBinding.product = productUIModel.product
        productUIModel.product.getOfferProductStrategy().let { offerProductStrategy ->
            productHomeOfferBinding.marketNameHomeOffer.text = offerProductStrategy?.marketName
        }
        Glide.with(context).load(productUIModel.product.imageUrl)
            .into(productHomeOfferBinding.imageHomeOffer)
        productHomeOfferBinding.onClickItemRecommendedProduct = onClickItem
    }

    companion object {
        fun from(
            parent: ViewGroup,
            goToOrder: ProductAdapter.OnClickProduct
        ): ProductHomeOfferViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemProductsHomeOfferBinding.inflate(layoutInflater, parent, false)
            return ProductHomeOfferViewHolder(parent.context, binding, goToOrder)
        }
    }
}