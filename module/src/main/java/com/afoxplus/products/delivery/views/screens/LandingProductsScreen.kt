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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.afoxplus.products.delivery.viewmodels.LandingProductViewModel
import com.afoxplus.products.entities.EstablishmentSection
import com.afoxplus.products.entities.Product
import com.afoxplus.uikit.common.UIState
import com.afoxplus.uikit.designsystem.atoms.UIKitText
import com.afoxplus.uikit.designsystem.businesscomponents.UIKitProductHorizontalItem
import com.afoxplus.uikit.designsystem.businesscomponents.UIKitProductItem
import com.afoxplus.uikit.designsystem.foundations.UIKitColorTheme
import com.afoxplus.uikit.designsystem.foundations.UIKitTheme
import com.afoxplus.uikit.designsystem.foundations.UIKitTypographyTheme
import com.afoxplus.uikit.designsystem.molecules.UIKitLoading
import kotlin.math.ceil

@Composable
fun LandingProductScreen(modifier: Modifier = Modifier) {
    LandingProductScreen(modifier = modifier, viewModel = hiltViewModel())
}

@Composable
internal fun LandingProductScreen(
    modifier: Modifier = Modifier,
    viewModel: LandingProductViewModel
) {
    val productSections by viewModel.productSections.collectAsState()
    Box(modifier = modifier.fillMaxSize()) {
        when (productSections) {
            is UIState.OnError -> HandleOnError(
                modifier = Modifier.align(Alignment.TopCenter),
                message = (productSections as UIState.OnError<List<EstablishmentSection>>).uiException.message.orEmpty()
            )

            is UIState.OnLoading -> UIKitLoading(
                modifier = Modifier.align(Alignment.Center),
                circleSize = UIKitTheme.spacing.spacing12
            )

            is UIState.OnSuccess -> HandleOnSuccess(
                modifier = Modifier.fillMaxSize(),
                productSections = (productSections as UIState.OnSuccess<List<EstablishmentSection>>).data,
                onProductClick = {
                    viewModel.onProductClicked(it)
                })
        }
    }
}

@Composable
internal fun HandleOnError(modifier: Modifier = Modifier, message: String) {
    UIKitText(modifier = modifier, text = message, style = UIKitTypographyTheme.header02SemiBold)
}

@Composable
internal fun HandleOnSuccess(
    modifier: Modifier = Modifier,
    productSections: List<EstablishmentSection>,
    onProductClick: (product: Product) -> Unit
) {
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
                            onClick = { onProductClick(product) }
                        )
                    } else {
                        UIKitProductItem(
                            imageUrl = product.imageUrl,
                            title = product.name,
                            description = product.description,
                            price = product.getPriceForSaleWithFormat(),
                            onClick = { onProductClick(product) }
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
    val rows = ceil(products.size / columns.toDouble()).toInt()

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
