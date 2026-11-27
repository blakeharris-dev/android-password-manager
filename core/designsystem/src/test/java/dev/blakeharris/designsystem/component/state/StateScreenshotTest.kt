package dev.blakeharris.designsystem.component.state

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onRoot
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.github.takahirom.roborazzi.captureRoboImage
import dev.blakeharris.designsystem.captureThemes
import dev.blakeharris.designsystem.theme.AppTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.GraphicsMode

@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
class StateScreenshotTest {

    @get:Rule
    val composeRule = createComposeRule()

    // ErrorContent

    @Test
    fun errorContent() = composeRule.captureThemes("ErrorContent") {
        ErrorContent(text = SHORT_TEXT)
    }

    @Test
    fun errorContent_longText() = composeRule.captureThemes("ErrorContent_longText") {
        ErrorContent(text = LONG_TEXT)
    }

    // InfoContent

    @Test
    fun infoContent() = composeRule.captureThemes("InfoContent") {
        InfoContent(text = SHORT_TEXT)
    }

    @Test
    fun infoContent_longText() = composeRule.captureThemes("InfoContent_longText") {
        InfoContent(text = LONG_TEXT)
    }

    // SuccessContent

    @Test
    fun successContent() = composeRule.captureThemes("SuccessContent") {
        SuccessContent(text = SHORT_TEXT)
    }

    @Test
    fun successContent_longText() = composeRule.captureThemes("SuccessContent_longText") {
        SuccessContent(text = LONG_TEXT)
    }

    // ErrorMessage

    @Test
    fun errorMessage() = composeRule.captureThemes("ErrorMessage") {
        ErrorMessage(text = SHORT_TEXT)
    }

    @Test
    fun errorMessage_longText() = composeRule.captureThemes("ErrorMessage_longText") {
        ErrorMessage(text = LONG_TEXT)
    }

    // LoadingContent
    //
    // The spinner animates forever, so the clock is paused before composing to capture
    // the same frame every run. captureThemes can't be used here: with the clock paused,
    // flipping the theme wouldn't recompose before the second capture.

    @Test
    fun loadingContent_light() = captureLoading(darkTheme = false)

    @Test
    fun loadingContent_dark() = captureLoading(darkTheme = true)

    private fun captureLoading(darkTheme: Boolean) {
        composeRule.mainClock.autoAdvance = false
        composeRule.setContent {
            AppTheme(darkTheme = darkTheme) { LoadingContent() }
        }

        val suffix = if (darkTheme) "dark" else "light"
        composeRule.onRoot().captureRoboImage("src/test/screenshots/LoadingContent_$suffix.png")
    }

    private companion object {
        const val SHORT_TEXT = "Something happened"
        const val LONG_TEXT =
            "A longer message that wraps across several lines to check the text stays centred and readable"
    }
}
