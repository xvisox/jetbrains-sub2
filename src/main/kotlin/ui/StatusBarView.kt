package ui

import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ui.common.Constants
import ui.common.ReusableModifiers

@Composable
fun StatusBarView(lines: MutableList<String>) = Box(ReusableModifiers.statusBarModifier) {
    val state = rememberLazyListState()

    LazyColumn(Modifier.fillMaxWidth(), state) {
        items(lines) {
            Text(it, color = Constants.textColor)
        }
    }
    VerticalScrollbar(
        modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight().width(12.dp),
        adapter = rememberScrollbarAdapter(
            scrollState = state
        )
    )
}