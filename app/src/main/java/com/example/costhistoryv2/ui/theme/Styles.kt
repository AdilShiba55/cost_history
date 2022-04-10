package com.example.costhistoryv2.ui.theme

import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable

object Styles {
    @Composable
    fun baseBlueField(): TextFieldColors {
        return TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = BoldBlue,
            unfocusedBorderColor = SimpleBlue
        )
    }
}