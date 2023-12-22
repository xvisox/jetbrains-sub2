package ui.common

import androidx.compose.ui.graphics.Color

class Constants {
    companion object {
        const val EXAMPLE_FILE_PATH = "./src/main/resources/example.main.kts"
        const val KOTLINC_SCRIPT_COMMAND = "kotlinc -script "
        const val BATCH_UPDATE_INTERVAL = 500L

        const val TEMP_FILE_PREFIX = "temp"
        const val TEMP_FILE_SUFFIX = ".main.kts"

        val textColor = Color(0xFFD6D6D6)
        val backgroundColor = Color(0xFF2E2E2E)
        val runButtonColor = Color(0xFF72C349)
        val navBarBackgroundColor = Color(0xFF21201E)
    }
}