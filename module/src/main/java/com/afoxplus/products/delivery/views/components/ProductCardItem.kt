package com.afoxplus.products.delivery.views.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.afoxplus.uikit.designsystem.atoms.UIKitIcon
import com.afoxplus.uikit.designsystem.atoms.UIKitText
import com.afoxplus.uikit.designsystem.foundations.UIKitColorTheme
import com.afoxplus.uikit.designsystem.foundations.UIKitIconTheme
import com.afoxplus.uikit.designsystem.foundations.UIKitTheme
import com.afoxplus.uikit.designsystem.foundations.UIKitTypographyTheme

@Composable
internal fun ProductCardItem() {
    Card(
        shape = RoundedCornerShape(UIKitTheme.spacing.spacing10),
        elevation = CardDefaults.cardElevation(
            defaultElevation = UIKitTheme.spacing.spacing02
        ),
        colors = CardDefaults.cardColors(containerColor = UIKitColorTheme.light01)
    ) {
        Column {
            AsyncImage(
                modifier = Modifier
                    .weight(1f)
                    .height(116.dp),
                model = "https://imag.bonviveur.com/lomo-saltado.jpg",
                contentDescription = ""
            )
            ProductItemInfo(verticalArrangement = Arrangement.spacedBy(UIKitTheme.spacing.spacing08))
        }
    }
}

@Composable
internal fun ProductCardHorizontalItem() {

    Card(
        shape = RoundedCornerShape(UIKitTheme.spacing.spacing10),
        elevation = CardDefaults.cardElevation(
            defaultElevation = UIKitTheme.spacing.spacing02
        ),
        colors = CardDefaults.cardColors(containerColor = UIKitColorTheme.light01)
    ) {
        Row {
            AsyncImage(
                modifier = Modifier
                    .weight(1f)
                    .height(116.dp),
                model = "https://imag.bonviveur.com/lomo-saltado.jpg",
                contentDescription = ""
            )
            ProductItemInfo(verticalArrangement = Arrangement.spacedBy(UIKitTheme.spacing.spacing04))
        }
    }

}

@Composable
internal fun ProductItemInfo(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical
) {
    Column(
        modifier = modifier.padding(UIKitTheme.spacing.spacing08),
        verticalArrangement = verticalArrangement
    ) {
        UIKitText(
            text = "Lomito saltado",
            style = UIKitTypographyTheme.header03SemiBold,
            color = UIKitColorTheme.secondaryColor,
            maxLines = 2
        )
        UIKitText(
            text = "200 gr meat + rice  lettuce + tomato",
            style = UIKitTypographyTheme.paragraph01,
            color = UIKitColorTheme.gray700,
            maxLines = 2
        )
        Row(modifier = Modifier.fillMaxWidth()) {
            UIKitText(
                modifier = Modifier.weight(1f),
                text = "S/ 22.00",
                style = UIKitTypographyTheme.header03Bold,
                color = UIKitColorTheme.primaryColor,
                maxLines = 1
            )
            PlusIcon()
        }

    }
}

@Composable
internal fun PlusIcon(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .size(25.dp)
            .clip(shape = RoundedCornerShape(UIKitTheme.spacing.spacing04))
            .background(UIKitColorTheme.orange50)
    ) {
        UIKitIcon(
            modifier = Modifier
                .size(UIKitTheme.spacing.spacing16)
                .align(Alignment.Center),
            icon = UIKitIconTheme.icon_plus,
            tint = UIKitColorTheme.orange700
        )
    }
}

@Preview(showBackground = true)
@Composable
internal fun PreviewCardProduct() {
    UIKitTheme {
        LazyVerticalGrid(
            contentPadding = PaddingValues(UIKitTheme.spacing.spacing16),
            verticalArrangement = Arrangement.spacedBy(UIKitTheme.spacing.spacing08),
            horizontalArrangement = Arrangement.spacedBy(UIKitTheme.spacing.spacing08),
            columns = GridCells.Fixed(2)
        ) {
            item {
                ProductCardItem()
            }
            item {
                ProductCardItem()
            }
            item {
                ProductCardItem()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
internal fun PreviewCardHorizontalProduct() {
    UIKitTheme {
        LazyColumn(
            contentPadding = PaddingValues(UIKitTheme.spacing.spacing16),
            verticalArrangement = Arrangement.spacedBy(UIKitTheme.spacing.spacing08)
        ) {
            item {
                ProductCardHorizontalItem()
            }
            item {
                ProductCardItem()
            }
            item {
                ProductCardItem()
            }
        }
    }
}