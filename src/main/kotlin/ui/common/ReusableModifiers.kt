package ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class ReusableModifiers {
    companion object {
        val dividerModifier = Modifier
            .height(5.dp)
            .fillMaxWidth()
            .background(color = Constants.backgroundColor.apply { copy(alpha = 0.5f) })

        val codeEditorModifier = Modifier
            .background(color = Constants.backgroundColor)
            .fillMaxWidth()
            .height(600.dp)
            .padding(16.dp)

        val navBarModifier = Modifier
            .background(color = Constants.navBarBackgroundColor)
            .padding(8.dp)
            .fillMaxWidth()
            .height(48.dp)

        val statusBarModifier = Modifier
            .background(color = Constants.backgroundColor)
            .padding(16.dp)
            .fillMaxSize()

        val circularProgressModifier = Modifier
            .width(40.dp)
            .padding(start = 10.dp, top = 10.dp)
    }
}
