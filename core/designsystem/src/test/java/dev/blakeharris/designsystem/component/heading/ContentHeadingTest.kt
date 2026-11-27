package dev.blakeharris.designsystem.component.heading

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.blakeharris.designsystem.captureThemes
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.GraphicsMode

@RunWith(AndroidJUnit4::class)
@GraphicsMode(GraphicsMode.Mode.NATIVE)
class ContentHeadingTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun contentHeading() = composeRule.captureThemes("ContentHeading") {
        ContentHeading(
            title = "Credentials",
            subtitle = "Your saved accounts"
        )
    }

    @Test
    fun contentHeading_longText() = composeRule.captureThemes("ContentHeading_longText") {
        ContentHeading(
            title = "A credential name long enough to wrap onto a second line",
            subtitle = "A subtitle that is also long enough to wrap, to check spacing and alignment hold up"
        )
    }
}
