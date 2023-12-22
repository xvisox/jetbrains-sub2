package ui

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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
        var result by remember { mutableStateOf(-1) }

        Button(
            onClick = {
                loading = true
                CoroutineScope(Dispatchers.Default).launch {
                    codeEditor.runKotlinScript(lines, codeText) { p1, p2 ->
                        loading = p1; result = p2
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(backgroundColor = Constants.runButtonColor),
            enabled = !loading
        ) {
            Text(Constants.RUN_CODE, fontSize = 20.sp)
        }

        if (loading) CircularProgressIndicator(ReusableModifiers.circularProgressModifier)
        else if (result != -1) {
            Text(
                "${Constants.EXIT_CODE}$result",
                fontSize = 32.sp,
                color = if (result == 0) Constants.textColor else Constants.failTextColor,
                modifier = Modifier.align(Alignment.CenterVertically).padding(start = 20.dp)
            )
        }
    }
