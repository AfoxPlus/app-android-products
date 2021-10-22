package com.afoxplus.products.delivery.views.adapters.viewholders

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.afoxplus.products.databinding.ItemProductsSaleBinding
import com.afoxplus.products.delivery.views.adapters.ProductSaleAdapter
import com.afoxplus.products.entities.Product
import com.bumptech.glide.Glide

internal class ProductSaleViewHolder private constructor(
    private val context: Context,
    private val productSaleBinding: ItemProductsSaleBinding,
    private val onClickItemSale: ProductSaleAdapter.OnClickItemRecommendedProduct
) : RecyclerView.ViewHolder(productSaleBinding.root) {

    fun bind(product: Product) {
        productSaleBinding.product = product
        Glide.with(context).load(product.imageUrl).into(productSaleBinding.productImage)
        productSaleBinding.onClickItemRecommendedProduct = onClickItemSale
        productSaleBinding.executePendingBindings()
    }

    companion object {
        fun from(
            parent: ViewGroup,
            goToOrder: ProductSaleAdapter.OnClickItemRecommendedProduct
        ): ProductSaleViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = ItemProductsSaleBinding.inflate(layoutInflater, parent, false)
            return ProductSaleViewHolder(parent.context, binding, goToOrder)
        }
    }
}