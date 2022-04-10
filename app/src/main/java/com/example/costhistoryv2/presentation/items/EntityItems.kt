package com.example.costhistory.presentation.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.costhistoryv2.R
import com.example.costhistoryv2.data.entities.CostCategory
import com.example.costhistoryv2.data.entities.CostCharacter
import com.example.costhistoryv2.ui.theme.BoldBlue
import com.example.costhistoryv2.ui.theme.Shapes
import com.example.costhistoryv2.ui.theme.SimpleGray
import com.example.costhistoryv2.ui.theme.Sizes

object EntityItems {
    @Composable
    fun CostCategoryItem(
        costCategory: CostCategory,
        itemClick: () -> Unit,
        itemLongClick: () -> Unit = {}
    ) {
        Box(
            modifier = Modifier
                .clickable {  }
                .pointerInput(Unit) {
                    detectTapGestures(
                        onPress = { itemClick() },
                        onDoubleTap = { /* Called on Double Tap */ },
                        onLongPress = { itemLongClick() },
                        onTap = { /* Called on Tap */ }
                    )
                }
        ) {
            Column(
                modifier = Modifier
                    .clip(Shapes.medium)
                    .background(BoldBlue)
                    .width(110.dp)
                    .padding(Sizes.paddingTiny),
//                    .height(100.dp)
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                val img =
                    if (costCategory.iHaveIcon()) costCategory.img else R.drawable.ic_hide_image
                val imgColor = if (costCategory.iHaveIcon()) Color.White else SimpleGray
                Image(
                    painter = painterResource(id = img),
                    contentDescription = "cost_category_icon",
                    colorFilter = ColorFilter.tint(imgColor),
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .height(24.dp)
                        .width(24.dp)
                )
                Text(
                    text = costCategory.title, color = Color.White,
                    maxLines = 1, overflow = TextOverflow.Ellipsis
                )
                val descriptionText =
                    costCategory.description.ifEmpty { "..." }
                Text(
                    text = descriptionText, color = Color.White, fontSize = Sizes.textTiny2,
                    maxLines = 1, overflow = TextOverflow.Ellipsis
                )
            }
        }
    }

    @Composable
    fun CostCharacterItem(
        costCharacter: CostCharacter,
        itemClick: () -> Unit,
        itemLongClick: () -> Unit
    ) {
        Box(modifier = Modifier
            .clickable {  }
            .pointerInput(Unit) {
                detectTapGestures(
                    onPress = { itemClick() },
                    onDoubleTap = { /* Called on Double Tap */ },
                    onLongPress = { itemLongClick() },
                    onTap = { /* Called on Tap */ }
                )
            }
        ) {
            Row(
                modifier = Modifier
                    .clip(Shapes.medium)
                    .background(BoldBlue)
//                        .width(100.dp)
                    .padding(Sizes.paddingTiny),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val img =
                    if (costCharacter.iHaveIcon()) costCharacter.img else R.drawable.ic_hide_image
                val imgColor = if (costCharacter.iHaveIcon()) Color.White else SimpleGray
                Image(
                    painter = painterResource(id = img),
                    contentDescription = "cost_category_icon",
                    colorFilter = ColorFilter.tint(imgColor),
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .height(18.dp)
                        .width(18.dp)
                )
                Spacer(
                    modifier = Modifier
                        .width(5.dp)
                )
                Text(
                    text = costCharacter.name, color = Color.White,
                    maxLines = 1, overflow = TextOverflow.Ellipsis,
                    textDecoration = TextDecoration.Underline
                )
            }
        }
    }
}