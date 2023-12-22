package ui.editor

import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ui.common.Constants
import ui.common.ReusableModifiers

@Composable
fun CodeEditorView(codeEditor: CodeEditor) = Column {
    val lines = remember { mutableStateListOf<String>() }
    // NavBarView
    Row(ReusableModifiers.navBarModifier) {
        Button(onClick = {
            CoroutineScope(Dispatchers.Default).launch {
                codeEditor.runKotlinScript(lines)
            }
        }) {
            Text("Button")
        }
    }
    Divider(ReusableModifiers.dividerModifier)

    // CodeEditorView
    Box(ReusableModifiers.codeEditorModifier) {
        var text by remember { mutableStateOf(codeEditor.getFileContent(Constants.EXAMPLE_FILE_PATH)) }

        BasicTextField(
            value = text,
            onValueChange = {
                text = it
            },
            modifier = Modifier.fillMaxSize(),
            textStyle = TextStyle(
                color = Constants.codeEditorTextColor,
                fontFamily = FontFamily.Monospace,
                fontSize = 15.sp
            )
        )
    }
    Divider(ReusableModifiers.dividerModifier)

    // StatusBarView
    Box(
        modifier = Modifier.fillMaxSize()
            .background(color = Color(180, 180, 180))
            .padding(10.dp)
    ) {

        val state = rememberLazyListState()

        LazyColumn(ReusableModifiers.statusBarModifier, state) {
            items(lines) {
                Text(it)
            }
        }
        VerticalScrollbar(
            modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
            adapter = rememberScrollbarAdapter(
                scrollState = state
            )
        )
    }
}