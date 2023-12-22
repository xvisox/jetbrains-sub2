package ui.navbar

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ui.common.Constants
import ui.common.ReusableModifiers
import ui.editor.CodeEditor

@Composable
fun NavBarView(codeEditor: CodeEditor, lines: MutableList<String>) = Row(ReusableModifiers.navBarModifier) {
    Button(
        onClick = {
            CoroutineScope(Dispatchers.Default).launch {
                codeEditor.runKotlinScript(lines)
            }
        }, colors = ButtonDefaults.buttonColors(backgroundColor = Constants.runButtonColor)
    ) {
        Text("Run Code", fontSize = 20.sp)
    }
}
