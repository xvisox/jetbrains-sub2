package ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import ui.common.Constants
import ui.common.ReusableModifiers
import ui.editor.SyntaxTransformation

@Composable
fun CodeView(codeText: String, onCodeTextChange: (String) -> Unit) =
    Box(ReusableModifiers.codeEditorModifier) {
        BasicTextField(
            value = codeText,
            onValueChange = onCodeTextChange,
            modifier = Modifier.fillMaxSize(),
            textStyle = TextStyle(
                color = Constants.textColor,
                fontFamily = FontFamily.Monospace,
                fontSize = 15.sp,
            ),
            visualTransformation = SyntaxTransformation(),
        )
    }