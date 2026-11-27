package dev.blakeharris.designsystem

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.onRoot
import com.github.takahirom.roborazzi.captureRoboImage
import dev.blakeharris.designsystem.theme.AppTheme

internal fun ComposeContentTestRule.captureThemes(name: String, content: @Composable () -> Unit) {
    var darkTheme by mutableStateOf(false)
    setContent {
        AppTheme(darkTheme = darkTheme) { content() }
    }

    onRoot().captureRoboImage("src/test/screenshots/${name}_light.png")
    darkTheme = true
    onRoot().captureRoboImage("src/test/screenshots/${name}_dark.png")
}
