package ui.editor

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import ui.common.Constants
import ui.common.ReusableModifiers
import java.io.BufferedReader
import java.io.InputStreamReader

@Composable
fun CodeEditorView(codeEditor: CodeEditor) = Column {
    var lines by remember { mutableStateOf("") }
    // NavBarView
    Row(ReusableModifiers.navBarModifier) {
        Button(onClick = {
            lines = ""
            GlobalScope.launch {
                val process = codeEditor.runKotlinScript(Constants.EXAMPLE_FILE_PATH).start()
                val reader = BufferedReader(InputStreamReader(process.inputStream))

                var line: String?
                while (reader.readLine().also { line = it } != null) {
                    lines += line + "\n"
                }

                process.waitFor()
            }
        }) {
            Text("Button")
        }
    }
    Divider(ReusableModifiers.dividerModifier)

    // CodeEditorView
    Box(ReusableModifiers.codeEditorModifier) {
        var text by remember { mutableStateOf(codeEditor.getFileContent(Constants.EXAMPLE_FILE_PATH)) }

        BasicTextField(
            value = text,
            onValueChange = {
                text = it
            },
            modifier = Modifier.fillMaxSize(),
            textStyle = TextStyle(
                color = Constants.codeEditorTextColor,
                fontFamily = FontFamily.Monospace,
                fontSize = 15.sp
            )
        )
    }
    Divider(ReusableModifiers.dividerModifier)

    // StatusBarView
    Column(ReusableModifiers.statusBarModifier) {
        Text("Read Data File")
//        BasicTextField(
//            value = lines,
//            onValueChange = {},
//            modifier = Modifier.fillMaxSize(),
//            textStyle = TextStyle(
//                color = Constants.codeEditorTextColor,
//                fontFamily = FontFamily.Monospace,
//                fontSize = 15.sp
//            )
//        )
        Text(lines)
    }

}