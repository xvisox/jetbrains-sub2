package ui

import androidx.compose.foundation.layout.Box
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.runtime.Composable
import ui.common.ReusableModifiers

@Composable
fun LinearProgressView(loading: Boolean, batchSize: Int, currentProgress: Float) = Box {
    if (loading && batchSize > 1) {
        LinearProgressIndicator(
            currentProgress,
            modifier = ReusableModifiers.linearProgressModifier
        )
    }
}