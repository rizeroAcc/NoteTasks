package com.example.notetasks.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay

@Composable
fun ModalNavigation(
    backStack: List<Any>,
    onDismiss: () -> Unit,
    entryProvider: (key: Any) -> NavEntry<Any>
) {
    val currentModal = backStack.lastOrNull() ?: return

    // Затемнение фона
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f))
            .clickable(onClick = onDismiss),
        contentAlignment = Alignment.Center
    ) {
        // Контент модального окна
        Box(
            modifier = Modifier
                .fillMaxSize(0.8f)
                .clickable(enabled = false) {} // Предотвращает закрытие при клике на контент
        ) {
            NavDisplay(
                backStack = backStack,
                onBack = onDismiss,
                entryProvider = entryProvider
            )
        }
    }
}