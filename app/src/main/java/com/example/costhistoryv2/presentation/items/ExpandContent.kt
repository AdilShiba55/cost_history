package com.example.costhistoryv2.presentation.items

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.example.costhistoryv2.R
import com.example.costhistoryv2.ui.theme.Sizes

object ExpandContent {
    @OptIn(ExperimentalMaterialApi::class)
    @Composable
    fun Expand(
        button: @Composable() () -> Unit,
        content: @Composable() () -> Unit,
        cardCorner: Dp = 0.dp,
        backgroundColor: Color = Color.White,
        arrowIconSize: Dp = 24.dp
    ) {
        var expanded by remember {
            mutableStateOf(false)
        }

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = LinearOutSlowInEasing
                    )
                ),
            shape = RoundedCornerShape(cardCorner),
            onClick = {
                expanded = !expanded
            },
            backgroundColor = backgroundColor
        ) {
            Column(
                modifier = Modifier
//                            .fillMaxSize()
                    .fillMaxWidth()
                    .padding(12.dp, 12.dp, 12.dp, 0.dp)
            ) {

                val arrow: Int =
                    if (expanded) R.drawable.ic_arrow_down2 else R.drawable.ic_arrow_up2

                button()


                if(expanded) {
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(10.dp)
                    )

                    content()
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = arrow),
                        contentDescription = "expand_arrow",
                        colorFilter = ColorFilter.tint(Color.Black),
                        contentScale = ContentScale.Fit,
                        alpha = 0.4f,
                        modifier = Modifier
                            .height(arrowIconSize)
                            .width(arrowIconSize)
                    )
                }
            }
        }
    }
    object Items {
        @Composable
        fun textWithArrow(
            expanded: Boolean = false,
            fontSize: TextUnit = Sizes.textMiddle2,
            title: String = "Expand Button"
        ) {
            val arrow: Int =
                if (expanded) R.drawable.ic_arrow_down else R.drawable.ic_arrow_up
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = title,
                    fontSize = fontSize,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}