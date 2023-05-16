package com.afoxplus.products.delivery.views.adapters.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.afoxplus.products.delivery.models.ProductUIModel

internal class ProductDiffUtilCallback : DiffUtil.ItemCallback<ProductUIModel>() {
    override fun areItemsTheSame(oldItem: ProductUIModel, newItem: ProductUIModel): Boolean =
        oldItem.product.code == newItem.product.code

    override fun areContentsTheSame(oldItem: ProductUIModel, newItem: ProductUIModel): Boolean =
        oldItem.product == newItem.product
}