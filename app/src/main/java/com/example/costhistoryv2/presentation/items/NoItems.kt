package com.example.costhistoryv2.presentation.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.example.costhistory.presentation.items.Buttons
import com.example.costhistoryv2.R
import com.example.costhistoryv2.ui.theme.*

object NoItems {
    @Composable
    fun Standard(
        color: Color = Color.Black,
        title: String = "Данные пусты",
        titleSize: TextUnit = Sizes.textMiddle,
        description: String = "",
        descriptionSize: TextUnit = Sizes.textSmall,
        icon: Int = R.drawable.ic_search,
        iconSize: Dp = 64.dp,
        backgroundColor: Color = SimpleGray,
        containerPadding: Dp = Sizes.paddingBig,
        withAddButton: Boolean = false,
        onClickAction: () -> Unit

    ) {
        Box(
            modifier = Modifier
                .clip(Shapes.small)
                .fillMaxWidth()
                .background(backgroundColor)
                .padding(containerPadding)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = "No result",
                    colorFilter = ColorFilter.tint(color),
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .height(iconSize)
                        .width(iconSize)
                )
                Text(
                    text = title, color = color, fontSize = titleSize
                )
                Spacer(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(7.dp)
                )
                if (description.isNotEmpty()) {
                    Text(
                        text = description, color = color,
                        textAlign = TextAlign.Center, fontSize = descriptionSize
                    )
                }
            }
            if (withAddButton) {
                Buttons.IconButton(
                    icon = R.drawable.ic_add,
                    containerModifier = Modifier
                        .zIndex(1f)
                        .offset(y = -containerPadding, x = containerPadding)
                        .align(alignment = Alignment.TopEnd),
                    onClickAction = onClickAction
                )
            }
        }
    }
}