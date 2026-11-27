package dev.blakeharris.designsystem.component.topbar

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.blakeharris.designsystem.theme.AppTheme
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppTopBarNavIconsTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun `BackNavIcon calls onClick when clicked`() {
        var clicked = false
        composeRule.setContent {
            AppTheme { AppTopBarNavIcons.BackNavIcon(onClick = { clicked = true }) }
        }

        composeRule.onNodeWithContentDescription(BACK_DESCRIPTION).performClick()

        assertTrue(clicked)
    }

    @Test
    fun `MenuNavIcon calls onClick when clicked`() {
        var clicked = false
        composeRule.setContent {
            AppTheme { AppTopBarNavIcons.MenuNavIcon(onClick = { clicked = true }) }
        }

        composeRule.onNodeWithContentDescription(MENU_DESCRIPTION).performClick()

        assertTrue(clicked)
    }

    @Test
    fun `nav icons are available inside AppTopBar`() {
        composeRule.setContent {
            AppTheme {
                AppTopBar(navigationIcon = { AppTopBarNavIcons.BackNavIcon(onClick = {}) })
            }
        }

        composeRule.onNodeWithContentDescription(BACK_DESCRIPTION).assertExists()
    }

    private companion object {
        // Keep in step with the content description string resources.
        const val BACK_DESCRIPTION = "Back"
        const val MENU_DESCRIPTION = "Open navigation menu"
    }
}
