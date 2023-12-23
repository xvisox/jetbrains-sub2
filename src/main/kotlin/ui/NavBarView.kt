package ui

import androidx.compose.foundation.layout.*
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
import kotlin.system.measureTimeMillis

@Composable
fun NavBarView(codeEditor: CodeEditor, outputLines: MutableList<String>, codeText: String) =
    Column {
        var loading by remember { mutableStateOf(false) }
        var currentProgress by remember { mutableStateOf(0f) }
        var estimatedRunningTime by remember { mutableStateOf(0.0) }
        var result by remember { mutableStateOf(-1) }
        var batchSize by remember { mutableStateOf(1) }

        Row(ReusableModifiers.navBarModifier) {
            Button(
                onClick = {
                    val previousElapsedTimes = LongArray(batchSize); loading = true
                    CoroutineScope(Dispatchers.Default).launch {
                        for (i in 1..batchSize) {
                            val elapsedTime = measureTimeMillis {
                                codeEditor.runKotlinScript(outputLines, codeText) { result = it }
                            }
                            currentProgress = i.toFloat() / batchSize
                            previousElapsedTimes[i - 1] = elapsedTime
                            estimatedRunningTime = codeEditor.estimateRunningTimeSecs(
                                previousElapsedTimes, batchSize, batchSize - i
                            )
                        }
                        loading = false; currentProgress = 0f; estimatedRunningTime = 0.0
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
            } else {
                CircularProgressIndicator(ReusableModifiers.circularProgressModifier)
                Text(
                    "${Constants.ESTIMATED_RUNNING_TIME}${String.format("%.2f", estimatedRunningTime)}s",
                    fontSize = 32.sp,
                    color = Constants.textColor,
                    modifier = Modifier.align(Alignment.CenterVertically).padding(start = 20.dp)
                )
            }

            Spacer(modifier = Modifier.weight(1f))
            Text(
                "${Constants.EXIT_CODE}${result}",
                fontSize = 32.sp,
                color = if (result == 0) Constants.textColor else Constants.failTextColor,
                modifier = Modifier.align(Alignment.CenterVertically).padding(start = 20.dp)
            )
        }

        LinearProgressView(loading, batchSize, currentProgress)
    }

