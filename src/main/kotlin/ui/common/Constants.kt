package ui.common

import androidx.compose.ui.graphics.Color

class Constants {
    companion object {
        // FIXME: move to properties file
        const val EXAMPLE_FILE_PATH = "./src/main/resources/example.main.kts"
        const val KOTLINC_SCRIPT_COMMAND = "kotlinc -script "
        const val BATCH_UPDATE_INTERVAL = 500L
        val codeEditorBackgroundColor = Color(0xFF8C9A9E)
        val codeEditorTextColor = Color(0xFF000000)
        val navBarBackgroundColor = Color(0xFF747578)
        val statusBarBackgroundColor = Color(0xFFD4F5F5)
    }
}