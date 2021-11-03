package com.afoxplus.products.delivery.views.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.afoxplus.products.delivery.models.ProductUIModel
import com.afoxplus.products.delivery.views.adapters.diffutils.ProductDiffUtilCallback
import com.afoxplus.products.delivery.views.adapters.viewholders.ProductAppetizerViewHolder
import com.afoxplus.products.delivery.views.adapters.viewholders.ProductItemViewHolder
import com.afoxplus.products.delivery.views.adapters.viewholders.ProductMenuViewHolder
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
            ProductUIModel.VIEW_TYPE_PRODUCT_MENU ->
                ProductMenuViewHolder.from(parent, onClickProduct)
            ProductUIModel.VIEW_TYPE_PRODUCT_APPETIZER ->
                ProductAppetizerViewHolder.from(parent)
            else -> ProductSaleViewHolder.from(parent, onClickProduct)
        }
    }

    override fun onBindViewHolder(holder: ProductItemViewHolder, position: Int) =
        holder.bind(getItem(position))

    override fun getItemViewType(position: Int): Int = getItem(position).viewType

    fun interface OnClickProduct {
        fun invoke(product: Product)
    }
}