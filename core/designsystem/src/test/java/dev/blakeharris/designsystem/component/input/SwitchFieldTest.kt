package dev.blakeharris.designsystem.component.input

import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertIsOff
import androidx.compose.ui.test.assertIsOn
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.blakeharris.designsystem.theme.AppTheme
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SwitchFieldTest {

    @get:Rule
    val composeRule = createComposeRule()

    private fun setSwitchField(checked: Boolean, enabled: Boolean = true, onCheckedChange: (Boolean) -> Unit = {}) {
        composeRule.setContent {
            AppTheme {
                SwitchField(
                    label = LABEL,
                    checked = checked,
                    onCheckedChange = onCheckedChange,
                    enabled = enabled
                )
            }
        }
    }

    @Test
    fun `SwitchField requests checked when clicked while unchecked`() {
        var requested: Boolean? = null
        setSwitchField(checked = false, onCheckedChange = { requested = it })

        composeRule.onNodeWithText(LABEL).performClick()

        assertEquals(true, requested)
    }

    @Test
    fun `SwitchField requests unchecked when clicked while checked`() {
        var requested: Boolean? = null
        setSwitchField(checked = true, onCheckedChange = { requested = it })

        composeRule.onNodeWithText(LABEL).performClick()

        assertEquals(false, requested)
    }

    @Test
    fun `SwitchField does not call onCheckedChange when disabled`() {
        var called = false
        setSwitchField(checked = false, enabled = false, onCheckedChange = { called = true })

        composeRule.onNodeWithText(LABEL).performClick()

        assertFalse(called)
    }

    @Test
    fun `SwitchField is reported as a switch to accessibility services`() {
        setSwitchField(checked = false)

        composeRule.onNodeWithText(LABEL)
            .assert(SemanticsMatcher.expectValue(SemanticsProperties.Role, Role.Switch))
    }

    @Test
    fun `SwitchField reports on when checked`() {
        setSwitchField(checked = true)

        composeRule.onNodeWithText(LABEL).assertIsOn()
    }

    @Test
    fun `SwitchField reports off when unchecked`() {
        setSwitchField(checked = false)

        composeRule.onNodeWithText(LABEL).assertIsOff()
    }

    @Test
    fun `SwitchField is reported as enabled when enabled`() {
        setSwitchField(checked = false)

        composeRule.onNodeWithText(LABEL).assertIsEnabled()
    }

    @Test
    fun `SwitchField is reported as disabled when disabled`() {
        setSwitchField(checked = false, enabled = false)

        composeRule.onNodeWithText(LABEL).assertIsNotEnabled()
    }

    private companion object {
        const val LABEL = "Two-factor enabled"
    }
}
