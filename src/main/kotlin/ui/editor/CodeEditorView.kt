package ui.editor

import androidx.compose.foundation.layout.Column
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import ui.StatusBarView
import ui.code.CodeView
import ui.common.ReusableModifiers
import ui.navbar.NavBarView

@Composable
fun CodeEditorView(codeEditor: CodeEditor) = Column {
    val lines = remember { mutableStateListOf<String>() }

    NavBarView(codeEditor, lines)
    Divider(ReusableModifiers.dividerModifier)

    CodeView(codeEditor)
    Divider(ReusableModifiers.dividerModifier)

    StatusBarView(lines)
}