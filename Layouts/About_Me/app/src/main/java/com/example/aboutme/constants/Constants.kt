package com.example.aboutme.constants

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.aboutme.R


object Constants{
    val NAMESTYLE = TextStyle(
        fontSize = 20.sp,
        fontFamily = FontFamily(Font(R.font.roboto)),
        textAlign = TextAlign.Center,
        color = Color.Black,
    )
}