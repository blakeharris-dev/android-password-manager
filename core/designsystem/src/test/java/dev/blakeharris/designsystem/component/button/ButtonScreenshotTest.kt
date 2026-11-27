package dev.blakeharris.designsystem.component.button

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.blakeharris.designsystem.captureThemes
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.GraphicsMode

@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
class ButtonScreenshotTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun primaryButton() = composeRule.captureThemes("PrimaryButton") {
        PrimaryButton(text = "Save", onClick = {})
    }

    @Test
    fun secondaryButton() = composeRule.captureThemes("SecondaryButton") {
        SecondaryButton(text = "Cancel", onClick = {})
    }

    @Test
    fun destructiveButton() = composeRule.captureThemes("DestructiveButton") {
        DestructiveButton(text = "Delete", onClick = {})
    }
}
