package com.example.calculator

import android.graphics.Color
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.sp

@Composable
fun CalculatorButton(
    symbol: String,
    modifier: Modifier,
    onClick: ()->Unit,
){
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .clip(MaterialTheme.shapes.extraLarge)
            .clickable{onClick()}
            .then(modifier)
    ){
        Text(
            text = symbol,
            color = when{
                symbol == "AC" || symbol == "Del" -> MaterialTheme.colorScheme.onTertiary
                symbol in listOf("+", "-", "*", "/","=") -> MaterialTheme.colorScheme.onPrimary
                else -> MaterialTheme.colorScheme.onSurface
            },
            fontSize = 34.sp
        )
    }
}