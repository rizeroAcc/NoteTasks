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
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay

@Composable
fun ModalNavigation(
    modifier: Modifier,
    backStack: List<Any>,
    onDismiss: () -> Unit,
    entryProvider: (key: Any) -> NavEntry<Any>
) {
    val currentModal = backStack.lastOrNull() ?: return

    // Затемнение фона
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.Black.copy(alpha = 0.5f))
            .clickable(onClick = onDismiss),
        contentAlignment = Alignment.Center
    ) {
        // Контент модального окна
        Box(
            modifier = Modifier
                .padding(24.dp)
                .clickable(enabled = false) {},// Предотвращает закрытие при клике на контент
            contentAlignment = Alignment.Center
        ) {
            NavDisplay(
                backStack = backStack,
                onBack = onDismiss,
                entryProvider = entryProvider,
                entryDecorators = listOf(
                    rememberSaveableStateHolderNavEntryDecorator(),
                    rememberViewModelStoreNavEntryDecorator()
                )
            )
        }
    }
}