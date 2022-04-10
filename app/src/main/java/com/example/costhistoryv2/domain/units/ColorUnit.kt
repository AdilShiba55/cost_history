package com.example.costhistory.units

import androidx.compose.ui.graphics.Color
import androidx.core.graphics.toColorInt

object ColorUnit {
    fun getColorFromHex(hex: String): Color {
        return Color(hex.toColorInt())
    }
}