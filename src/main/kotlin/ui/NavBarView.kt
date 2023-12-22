package ui

import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ui.common.Constants
import ui.common.ReusableModifiers
import ui.editor.CodeEditor

@Composable
fun NavBarView(codeEditor: CodeEditor, lines: MutableList<String>, codeText: String) =
    Row(ReusableModifiers.navBarModifier) {
        var loading by remember { mutableStateOf(false) }

        Button(
            onClick = {
                loading = true
                CoroutineScope(Dispatchers.Default).launch {
                    codeEditor.runKotlinScript(lines, codeText) {
                        loading = it
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Constants.runButtonColor),
            enabled = !loading
        ) {
            Text("Run Code", fontSize = 20.sp)
        }

        if (loading) CircularProgressIndicator(ReusableModifiers.circularProgressModifier)
    }
