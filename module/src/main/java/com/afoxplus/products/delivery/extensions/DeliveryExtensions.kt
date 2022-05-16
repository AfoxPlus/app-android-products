package com.afoxplus.products.delivery.extensions

import com.afoxplus.products.delivery.models.EmptyStringsUIModel
import com.afoxplus.products.delivery.viewmodels.ProductViewModel

internal fun <T> EmptyStringsUIModel.toEmptyProduct(): ProductViewModel.EmptyProduct<T> =
    ProductViewModel.EmptyProduct(title = this.lblTitle, description = this.lblDescription)