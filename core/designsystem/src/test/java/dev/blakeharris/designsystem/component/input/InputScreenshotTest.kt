package dev.blakeharris.designsystem.component.input

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.text.input.KeyboardType
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.blakeharris.designsystem.captureThemes
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.GraphicsMode

@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
class InputScreenshotTest {

    @get:Rule
    val composeRule = createComposeRule()

    // AppTextField

    @Test
    fun appTextField_empty() = composeRule.captureThemes("AppTextField_empty") {
        AppTextField(value = "", onValueChange = {}, label = "Username")
    }

    @Test
    fun appTextField_filled() = composeRule.captureThemes("AppTextField_filled") {
        AppTextField(value = "blake", onValueChange = {}, label = "Username")
    }

    @Test
    fun appTextField_optional() = composeRule.captureThemes("AppTextField_optional") {
        AppTextField(value = "", onValueChange = {}, label = "Username", optional = true)
    }

    @Test
    fun appTextField_readOnly() = composeRule.captureThemes("AppTextField_readOnly") {
        AppTextField(value = "blake", onValueChange = {}, label = "Username", readOnly = true)
    }

    @Test
    fun appTextField_multiline() = composeRule.captureThemes("AppTextField_multiline") {
        AppTextField(
            value = "A longer note that should wrap across more than one line of the field",
            onValueChange = {},
            label = "Notes",
            minLines = 3
        )
    }

    // SecretField

    @Test
    fun secretField_filled() = composeRule.captureThemes("SecretField_filled") {
        SecretField(value = "hunter2", onValueChange = {}, label = "Password")
    }

    @Test
    fun secretField_pin() = composeRule.captureThemes("SecretField_pin") {
        SecretField(
            value = "1234",
            onValueChange = {},
            label = "Service PIN",
            optional = true,
            keyboardType = KeyboardType.NumberPassword
        )
    }

    // SwitchField

    @Test
    fun switchField_unchecked() = composeRule.captureThemes("SwitchField_unchecked") {
        SwitchField(label = "Two-factor enabled", checked = false, onCheckedChange = {})
    }

    @Test
    fun switchField_checked() = composeRule.captureThemes("SwitchField_checked") {
        SwitchField(label = "Two-factor enabled", checked = true, onCheckedChange = {})
    }

    @Test
    fun switchField_supportingText() = composeRule.captureThemes("SwitchField_supportingText") {
        SwitchField(
            label = "Two-factor enabled",
            supportingText = "Requires a code from your authenticator app",
            checked = true,
            onCheckedChange = {}
        )
    }

    @Test
    fun switchField_disabled() = composeRule.captureThemes("SwitchField_disabled") {
        SwitchField(label = "Two-factor enabled", checked = true, onCheckedChange = {}, enabled = false)
    }

    @Test
    fun switchField_longLabel() = composeRule.captureThemes("SwitchField_longLabel") {
        SwitchField(
            label = "A label long enough to wrap onto a second line beside the switch",
            checked = false,
            onCheckedChange = {}
        )
    }
}
