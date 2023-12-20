package ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

class ReusableModifiers {
    companion object {
        val dividerModifier = Modifier
            .height(3.dp)
            .fillMaxWidth()
            .background(color = Color.Black)

        val codeEditorModifier = Modifier
            .background(color = Constants.codeEditorBackgroundColor)
            .fillMaxWidth()
            .height(700.dp)
            .padding(16.dp)

        val navBarModifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(color = Constants.navBarBackgroundColor)

        val statusBarModifier = Modifier
            .fillMaxSize()
            .background(color = Constants.statusBarBackgroundColor)
    }
}
