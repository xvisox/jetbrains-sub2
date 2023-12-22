package ui.common

import androidx.compose.ui.graphics.Color

class Constants {
    companion object {
        // FIXME: move to properties file
        const val EXAMPLE_FILE_PATH = "./src/main/resources/example.main.kts"
        const val KOTLINC_SCRIPT_COMMAND = "kotlinc -script "
        const val BATCH_UPDATE_INTERVAL = 500L
        val textColor = Color(0xFFD6D6D6)
        val backgroundColor = Color(0xFF2E2E2E)
        val runButtonColor = Color(0xFF72C349)
        val navBarBackgroundColor = Color(0xFF21201E)
    }
}