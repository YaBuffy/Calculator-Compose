package com.example.calculator

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calculator.historyDB.HistoryEntity

@Composable
fun HistoryScreen(history: List<HistoryEntity>, onBack:() -> Unit){
    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .background(color = MaterialTheme.colorScheme.background)
        .systemBarsPadding()){
        item {
            IconButton(onClick = onBack) { Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Back",
                tint = MaterialTheme.colorScheme.onBackground) }
        }
        items(history){item ->
            Text(
                text = "${item.expression} = ${item.result}",
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 34.sp,
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.surfaceVariant, shape = MaterialTheme.shapes.extraLarge)
                    .padding(horizontal = 12.dp, vertical = 12.dp),
                maxLines = 2,
                lineHeight = 32.sp
            )
        }
    }
}