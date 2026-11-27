package dev.blakeharris.designsystem.component.fab

import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.disabled
import androidx.compose.ui.semantics.semantics

@Composable
fun AppFab(onClick: () -> Unit, icon: @Composable () -> Unit, modifier: Modifier = Modifier, enabled: Boolean = true) {
    if (enabled) {
        EnabledAppFab(
            onClick = onClick,
            icon = icon,
            modifier = modifier
        )
    } else {
        DisabledAppFab(
            icon = icon,
            modifier = modifier
        )
    }
}

@Composable
internal fun DisabledAppFab(icon: @Composable () -> Unit, modifier: Modifier = Modifier) {
    FloatingActionButton(
        onClick = {},
        modifier = modifier.semantics { disabled() },
        containerColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.12f),
        contentColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.38f)
    ) { icon() }
}

@Composable
internal fun EnabledAppFab(onClick: () -> Unit, icon: @Composable () -> Unit, modifier: Modifier = Modifier) {
    FloatingActionButton(
        onClick = onClick,
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.primaryContainer,
        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
    ) { icon() }
}
