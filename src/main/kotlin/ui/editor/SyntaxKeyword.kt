package ui.editor

import androidx.compose.ui.graphics.Color
import ui.common.Constants

enum class SyntaxKeyword(val keyword: String, val color: Color) {
    CLASS("class", Constants.syntaxRed),
    CONTINUE("continue", Constants.syntaxRed),
    BREAK("break", Constants.syntaxRed),
    DO("do", Constants.syntaxRed),
    ELSE("else", Constants.syntaxRed),
    FUN("fun", Constants.syntaxRed),
    FOR("for", Constants.syntaxRed),
    IF("if", Constants.syntaxRed),
    IN("in", Constants.syntaxRed),
    IMPORT("import", Constants.syntaxRed),
    VAL("val", Constants.syntaxRed),
    VAR("var", Constants.syntaxRed),
    RETURN("return", Constants.syntaxRed),
}