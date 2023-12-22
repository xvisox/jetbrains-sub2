package ui.editor

import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration

class SyntaxTransformation : VisualTransformation {

    override fun filter(text: AnnotatedString): TransformedText {
        return TransformedText(
            buildAnnotatedStringWithSyntaxHighlighting(text),
            OffsetMapping.Identity
        )
    }

    private fun buildAnnotatedStringWithSyntaxHighlighting(text: AnnotatedString): AnnotatedString {
        return buildAnnotatedString {
            append(text)
            for (keyword in SyntaxKeyword.entries) {
                val keywordRegex = "\\b${Regex.escape(keyword.keyword)}\\b".toRegex()

                keywordRegex.findAll(text.toString()).forEach { result ->
                    val startIndex = result.range.first
                    val endIndex = result.range.last + 1

                    addStyle(
                        style = SpanStyle(
                            color = keyword.color,
                            textDecoration = TextDecoration.None
                        ),
                        start = startIndex, end = endIndex
                    )
                }
            }
        }
    }
}

