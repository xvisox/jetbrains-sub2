package ui.editor

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Divider
import androidx.compose.runtime.*
import ui.statusbar.StatusBarView
import ui.code.CodeView
import ui.common.Constants
import ui.common.ReusableModifiers
import ui.navbar.NavBarView

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