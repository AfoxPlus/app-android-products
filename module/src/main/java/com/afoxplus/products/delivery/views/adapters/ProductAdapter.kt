package com.afoxplus.products.delivery.views.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.afoxplus.products.delivery.models.ProductUIModel
import com.afoxplus.products.delivery.views.adapters.viewholders.ProductItemViewHolder
import com.afoxplus.products.delivery.views.adapters.viewholders.ProductOfferViewHolder
import com.afoxplus.products.delivery.views.adapters.viewholders.ProductSaleViewHolder
import com.afoxplus.products.entities.Product

internal class ProductAdapter :
    ListAdapter<ProductUIModel, ProductItemViewHolder>(ProductDiffUtilCallback()) {

    private var onClickProduct: OnClickProduct = OnClickProduct {}

    fun setOnClickProductListener(listener: OnClickProduct) {
        onClickProduct = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductItemViewHolder {
        return when (viewType) {
            ProductUIModel.VIEW_TYPE_PRODUCT_SALE ->
                ProductSaleViewHolder.from(parent, onClickProduct)
            ProductUIModel.VIEW_TYPE_PRODUCT_OFFER ->
                ProductOfferViewHolder.from(parent, onClickProduct)
            else -> ProductSaleViewHolder.from(parent, onClickProduct)
        }
    }

    override fun onBindViewHolder(holder: ProductItemViewHolder, position: Int) =
        holder.bind(getItem(position))

    class ProductDiffUtilCallback : DiffUtil.ItemCallback<ProductUIModel>() {
        override fun areItemsTheSame(oldItem: ProductUIModel, newItem: ProductUIModel): Boolean =
            oldItem.product.code == newItem.product.code

        override fun areContentsTheSame(oldItem: ProductUIModel, newItem: ProductUIModel): Boolean =
            oldItem.product == newItem.product
    }

    override fun getItemViewType(position: Int): Int = getItem(position).viewType

    fun interface OnClickProduct {
        fun invoke(product: Product)
    }
}