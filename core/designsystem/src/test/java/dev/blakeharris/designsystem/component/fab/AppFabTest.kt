package dev.blakeharris.designsystem.component.fab

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.blakeharris.designsystem.theme.AppTheme
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppFabTest {

    @get:Rule
    val composeRule = createComposeRule()

    private fun setFab(enabled: Boolean, onClick: () -> Unit = {}) {
        composeRule.setContent {
            AppTheme {
                AppFab(
                    onClick = onClick,
                    icon = { Icon(Icons.Default.Add, contentDescription = "Add") },
                    enabled = enabled
                )
            }
        }
    }

    @Test
    fun `AppFab calls onClick when enabled`() {
        var clicked = false
        setFab(enabled = true, onClick = { clicked = true })

        composeRule.onNodeWithContentDescription("Add").performClick()

        assertTrue(clicked)
    }

    @Test
    fun `AppFab does not call onClick when disabled`() {
        var clicked = false
        setFab(enabled = false, onClick = { clicked = true })

        composeRule.onNodeWithContentDescription("Add").performClick()

        assertFalse(clicked)
    }

    @Test
    fun `AppFab is reported as enabled to accessibility services when enabled`() {
        setFab(enabled = true)

        composeRule.onNodeWithContentDescription("Add").assertIsEnabled()
    }

    @Test
    fun `AppFab is reported as disabled to accessibility services when disabled`() {
        setFab(enabled = false)

        composeRule.onNodeWithContentDescription("Add").assertIsNotEnabled()
    }
}
