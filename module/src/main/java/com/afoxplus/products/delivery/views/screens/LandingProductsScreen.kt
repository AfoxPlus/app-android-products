package com.afoxplus.products.delivery.views.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.afoxplus.products.delivery.viewmodels.LandingProductViewModel
import com.afoxplus.products.entities.Product
import com.afoxplus.uikit.designsystem.atoms.UIKitText
import com.afoxplus.uikit.designsystem.businesscomponents.UIKitProductHorizontalItem
import com.afoxplus.uikit.designsystem.businesscomponents.UIKitProductItem
import com.afoxplus.uikit.designsystem.foundations.UIKitColorTheme
import com.afoxplus.uikit.designsystem.foundations.UIKitTheme
import com.afoxplus.uikit.designsystem.foundations.UIKitTypographyTheme
import kotlin.math.ceil

@Composable()
fun LandingProductScreen(modifier: Modifier = Modifier) {
    LandingProductScreen(modifier = modifier, viewModel = hiltViewModel())
}

@Composable
internal fun LandingProductScreen(
    modifier: Modifier = Modifier,
    viewModel: LandingProductViewModel
) {
    val productSections by viewModel.productSections.collectAsState()

    LazyColumn(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(UIKitTheme.spacing.spacing12),
        contentPadding = PaddingValues(UIKitTheme.spacing.spacing12)
    ) {
        productSections.forEach { section ->
            item {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(
                            color = UIKitColorTheme.orange50,
                            shape = RoundedCornerShape(UIKitTheme.spacing.spacing08)
                        )
                        .padding(UIKitTheme.spacing.spacing08)
                ) {
                    UIKitText(
                        text = section.sectionName,
                        style = UIKitTypographyTheme.header05Bold,
                        color = UIKitColorTheme.orange700
                    )
                }

            }

            item {
                NonScrollableGrid(
                    products = section.products,
                    columns = section.productGridColumnSize
                ) { product ->
                    if (section.productGridColumnSize == 1) {
                        UIKitProductHorizontalItem(
                            imageUrl = product.imageUrl,
                            title = product.name,
                            description = product.description,
                            price = product.getPriceForSaleWithFormat(),
                            onClick = {
                                viewModel.onProductClicked(product)
                            }
                        )
                    } else {
                        UIKitProductItem(
                            imageUrl = product.imageUrl,
                            title = product.name,
                            description = product.description,
                            price = product.getPriceForSaleWithFormat(),
                            onClick = {
                                viewModel.onProductClicked(product)
                            }
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun NonScrollableGrid(
    products: List<Product>,
    columns: Int,
    itemContent: @Composable (Product) -> Unit
) {
    val rows = ceil(products.size / columns.toDouble()).toInt()  // Calculate number of rows

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(UIKitTheme.spacing.spacing12)
    ) {
        for (rowIndex in 0 until rows) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(UIKitTheme.spacing.spacing12)
            ) {
                for (columnIndex in 0 until columns) {
                    val itemIndex = rowIndex * columns + columnIndex
                    if (itemIndex < products.size) {
                        Box(modifier = Modifier.weight(1f)) {
                            itemContent(products[itemIndex])
                        }
                    } else {
                        Spacer(modifier = Modifier.weight(1f))
                    }
                }
            }
        }
    }
}
