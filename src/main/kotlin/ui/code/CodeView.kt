package ui.code

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import ui.common.Constants
import ui.common.ReusableModifiers
import ui.editor.CodeEditor

@Composable
fun CodeView(codeEditor: CodeEditor) = Box(ReusableModifiers.codeEditorModifier) {
    var text by remember { mutableStateOf(codeEditor.getFileContent(Constants.EXAMPLE_FILE_PATH)) }

    BasicTextField(
        value = text,
        onValueChange = {
            text = it
        },
        modifier = Modifier.fillMaxSize(),
        textStyle = TextStyle(
            color = Constants.textColor,
            fontFamily = FontFamily.Monospace,
            fontSize = 15.sp
        )
    )
}