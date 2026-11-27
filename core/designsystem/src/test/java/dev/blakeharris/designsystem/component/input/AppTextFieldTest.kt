package dev.blakeharris.designsystem.component.input

import androidx.compose.ui.semantics.SemanticsActions
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.blakeharris.designsystem.theme.AppTheme
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AppTextFieldTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun `AppTextField shows the label without a suffix when required`() {
        composeRule.setContent {
            AppTheme {
                AppTextField(value = "", onValueChange = {}, label = "Username")
            }
        }

        composeRule.onNodeWithText("Username").assertExists()
        composeRule.onNodeWithText("Username (optional)").assertDoesNotExist()
    }

    @Test
    fun `AppTextField appends optional to the label when optional`() {
        composeRule.setContent {
            AppTheme {
                AppTextField(value = "", onValueChange = {}, label = "Username", optional = true)
            }
        }

        composeRule.onNodeWithText("Username (optional)").assertExists()
    }

    @Test
    fun `AppTextField reports typed text through onValueChange`() {
        var value = ""
        composeRule.setContent {
            AppTheme {
                AppTextField(value = "", onValueChange = { value = it }, label = "Username")
            }
        }

        composeRule.onNodeWithText("Username").performTextInput("blake")

        assertEquals("blake", value)
    }

    @Test
    fun `AppTextField cannot be edited when read only`() {
        composeRule.setContent {
            AppTheme {
                AppTextField(value = "fixed", onValueChange = {}, label = "Username", readOnly = true)
            }
        }

        // Compose drops the SetText action from read-only fields. If this fails on your
        // Compose version, assert instead that performTextInput leaves the value unchanged.
        composeRule.onNodeWithText("Username")
            .assert(SemanticsMatcher.keyNotDefined(SemanticsActions.SetText))
    }

    @Test
    fun `AppTextField accepts multiple minimum lines without crashing`() {
        composeRule.setContent {
            AppTheme {
                AppTextField(value = "", onValueChange = {}, label = "Notes", minLines = 3)
            }
        }

        composeRule.onNodeWithText("Notes").assertExists()
    }
}
