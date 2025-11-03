package com.example.task_feature.presentation.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp

@Composable
fun SimpleTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle.Default,
    cursorColor: Color = Color.Black,
    singleLine: Boolean = false,
    maxLines: Int = Int.MAX_VALUE,
    hint: String = ""
) {
    var isFocused by remember { mutableStateOf(false) }
    val showHint = value.isEmpty() && !isFocused
    BasicTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .onFocusChanged { focusState ->
                isFocused = focusState.isFocused
            }
            .clickable { /* Handle click if needed */ },
        textStyle = textStyle,
        cursorBrush = SolidColor(cursorColor),
        singleLine = singleLine,
        maxLines = maxLines,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(0.dp)
                    .border(
                        width = 0.dp,
                        color = Color.Transparent
                    )
            ) {
                if (showHint) {
                    Text(
                        text = hint,
                        style = textStyle,
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterStart)
                    )
                }
                innerTextField()

                // Анимированный курсор
                if (isFocused) {
                    AnimatedVisibility(
                        visible = isFocused,
                        enter = fadeIn(),
                        exit = fadeOut()
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(2.dp)
                                .background(cursorColor)
                                .align(Alignment.CenterStart)
                        )
                    }
                }
            }
        }
    )
}