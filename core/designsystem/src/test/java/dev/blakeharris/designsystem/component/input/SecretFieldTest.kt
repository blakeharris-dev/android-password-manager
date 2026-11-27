package dev.blakeharris.designsystem.component.input

import androidx.compose.ui.semantics.SemanticsProperties
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
class SecretFieldTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun `SecretField is marked as a password field`() {
        composeRule.setContent {
            AppTheme {
                SecretField(value = "hunter2", onValueChange = {}, label = "Password")
            }
        }

        composeRule.onNodeWithText("Password")
            .assert(SemanticsMatcher.keyIsDefined(SemanticsProperties.Password))
    }

    @Test
    fun `SecretField reports typed text through onValueChange`() {
        var value = ""
        composeRule.setContent {
            AppTheme {
                SecretField(value = "", onValueChange = { value = it }, label = "Password")
            }
        }

        composeRule.onNodeWithText("Password").performTextInput("hunter2")

        assertEquals("hunter2", value)
    }
}
