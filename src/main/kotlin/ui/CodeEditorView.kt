package ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Divider
import androidx.compose.runtime.*
import ui.common.Constants
import ui.common.ReusableModifiers
import ui.editor.CodeEditor

@Composable
fun CodeEditorView(codeEditor: CodeEditor) = Column {
    val outputLines = remember { mutableStateListOf<String>() }
    var codeText by remember { mutableStateOf(codeEditor.getFileContent(Constants.EXAMPLE_FILE_PATH)) }

    NavBarView(codeEditor, outputLines, codeText)
    Divider(ReusableModifiers.dividerModifier)

    CodeView(codeText) { codeText = it }
    Divider(ReusableModifiers.dividerModifier)

    StatusBarView(outputLines)
}