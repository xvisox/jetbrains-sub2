import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import ui.editor.CodeEditorView
import ui.editor.CodeEditor


fun main() = application {
    val windowState = rememberWindowState(placement = WindowPlacement.Maximized)

    Window(onCloseRequest = ::exitApplication, state = windowState) {
        CodeEditorView(CodeEditor())
    }
}
