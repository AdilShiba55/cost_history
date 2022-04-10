package com.example.costhistory.presentation.items

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.costhistoryv2.ui.theme.SimpleGray
import com.example.costhistoryv2.ui.theme.SimpleGreen
import com.example.costhistoryv2.ui.theme.Sizes
import com.example.costhistoryv2.ui.theme.WeakGreen

object Buttons {
    @Composable
    fun IconButton(
        icon: Int,
        iconColor: Color = Color.White,
        iconSize: Dp = Sizes.buttonMiddle,
        backgroundSize: Dp = 0.dp,
        shape: RoundedCornerShape = CircleShape,
        backgroundColor: Color = SimpleGreen,
        borderSize: Dp = 0.dp,
        borderColor: Color = SimpleGray,
        containerModifier: Modifier = Modifier,
        onClickAction: () -> Unit = {}
    ) {
        val resources = LocalContext.current.resources
        val iconName = resources.getResourceEntryName(icon)
        Box(modifier = containerModifier) {
            androidx.compose.material.IconButton(
                modifier = Modifier
                    .clip(shape)
                    .border(borderSize, borderColor, shape)
                    .background(backgroundColor)
                    .size(iconSize + backgroundSize),
                onClick = { onClickAction() }
            ) {
                Image(
                    painter = painterResource(id = icon),
                    contentDescription = iconName,
                    colorFilter = ColorFilter.tint(iconColor),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(iconSize)
                        .width(iconSize)
                )
            }
        }
    }

    @Composable
    fun StandardButton(
        text: String = "Жмяк",
        fontColor: Color = Color.White,
        fontSize: TextUnit = Sizes.textMiddle2,
        backgroundColor: Color = WeakGreen,
        action: () -> Unit
    ) {
        Button(
            modifier = Modifier
                .fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(backgroundColor = backgroundColor),
            onClick = { action() }
        ) {
            Text(text = text, color = fontColor, fontSize = fontSize)
        }
    }
}