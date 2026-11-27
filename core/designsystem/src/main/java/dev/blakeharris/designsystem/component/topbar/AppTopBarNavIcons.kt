package dev.blakeharris.designsystem.component.topbar

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import dev.blakeharris.designsystem.R
import dev.blakeharris.designsystem.icon.AppIcons

object AppTopBarNavIcons {
    @Composable
    fun BackNavIcon(onClick: () -> Unit) {
        IconButton(onClick = onClick) {
            Icon(
                imageVector = AppIcons.Back,
                contentDescription = stringResource(R.string.designsystem_app_top_bar_back)
            )
        }
    }

    @Composable
    fun MenuNavIcon(onClick: () -> Unit) {
        IconButton(onClick = onClick) {
            Icon(
                imageVector = AppIcons.Menu,
                contentDescription = stringResource(R.string.designsystem_app_top_bar_open_navigation_menu)
            )
        }
    }
}
