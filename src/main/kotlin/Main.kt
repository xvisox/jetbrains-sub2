import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowPlacement
import androidx.compose.ui.window.application
import androidx.compose.ui.window.rememberWindowState
import ui.editor.CodeEditor
import ui.editor.CodeEditorView

@Preview
@Composable
fun mainPreview() {
    CodeEditorView(CodeEditor())
}

fun main() = application {
    val windowState = rememberWindowState(placement = WindowPlacement.Maximized)

    Window(onCloseRequest = ::exitApplication, state = windowState) {
        CodeEditorView(CodeEditor())
    }
}
