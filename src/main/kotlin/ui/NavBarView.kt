package ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
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
    Column {
        var loading by remember { mutableStateOf(false) }
        var currentProgress by remember { mutableStateOf(0f) }
        var result by remember { mutableStateOf(-1) }
        var batchSize by remember { mutableStateOf(1) }

        Row(ReusableModifiers.navBarModifier) {
            Button(
                onClick = {
                    loading = true
                    CoroutineScope(Dispatchers.Default).launch {
                        for (i in 1..batchSize) {
                            codeEditor.runKotlinScript(lines, codeText) { result = it }
                            currentProgress = (i + 1).toFloat() / batchSize
                        }
                        loading = false; currentProgress = 0f
                    }
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = Constants.runButtonColor),
                enabled = !loading
            ) {
                Text(Constants.RUN_CODE, fontSize = 31.sp, color = Constants.textColor)
            }

            if (!loading) {
                TextField(
                    value = batchSize.toString(),
                    onValueChange = { batchSize = it.toIntOrNull() ?: 1 },
                    label = { Text(Constants.BATCH_SIZE, color = Constants.textColor) },
                    modifier = Modifier.align(Alignment.CenterVertically).width(150.dp).padding(start = 20.dp),
                    textStyle = TextStyle.Default.copy(color = Constants.textColor, fontSize = 20.sp)
                )
            } else CircularProgressIndicator(ReusableModifiers.circularProgressModifier)

            if (result != -1) {
                Text(
                    "${Constants.EXIT_CODE}$result",
                    fontSize = 32.sp,
                    color = if (result == 0) Constants.textColor else Constants.failTextColor,
                    modifier = Modifier.align(Alignment.CenterVertically).padding(start = 20.dp)
                )
            }
        }
        if (loading && batchSize > 1) {
            LinearProgressIndicator(
                currentProgress,
                modifier = ReusableModifiers.linearProgressModifier
            )
        }
    }

