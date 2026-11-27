package dev.blakeharris.designsystem.component.topbar

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.blakeharris.designsystem.captureThemes
import dev.blakeharris.designsystem.icon.AppIcons
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.GraphicsMode

@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
class TopBarScreenshotTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun appTopBar_titleOnly() = composeRule.captureThemes("AppTopBar_titleOnly") {
        AppTopBar()
    }

    @Test
    fun appTopBar_back() = composeRule.captureThemes("AppTopBar_back") {
        AppTopBar(navigationIcon = { AppTopBarNavIcons.BackNavIcon(onClick = {}) })
    }

    @Test
    fun appTopBar_menu() = composeRule.captureThemes("AppTopBar_menu") {
        AppTopBar(navigationIcon = { AppTopBarNavIcons.MenuNavIcon(onClick = {}) })
    }

    // Catches the action icon colour: it should match the navigation icon and title.
    @Test
    fun appTopBar_action() = composeRule.captureThemes("AppTopBar_action") {
        AppTopBar(
            navigationIcon = { AppTopBarNavIcons.BackNavIcon(onClick = {}) },
            actions = {
                IconButton(onClick = {}) {
                    Icon(imageVector = AppIcons.Info, contentDescription = "Info")
                }
            }
        )
    }
}
