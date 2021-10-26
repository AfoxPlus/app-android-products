package com.afoxplus.products.delivery.views.adapters.viewholders

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.afoxplus.products.delivery.models.ProductUIModel

internal abstract class ProductItemViewHolder(private val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root), ProductBindViewHolder {

    override fun bind(productUIModel: ProductUIModel) {
        binding.executePendingBindings()
    }
}

internal fun interface ProductBindViewHolder {
    fun bind(productUIModel: ProductUIModel)
}