package dev.blakeharris.designsystem.component.state

import androidx.compose.ui.semantics.LiveRegionMode
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import dev.blakeharris.designsystem.theme.AppTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ErrorMessageTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun `ErrorMessage is announced politely to accessibility services`() {
        composeRule.setContent {
            AppTheme { ErrorMessage(text = MESSAGE) }
        }

        // Relies on the Surface using semantics(mergeDescendants = true) { liveRegion = Polite },
        // so the text and the live region sit on one merged node.
        composeRule.onNodeWithText(MESSAGE)
            .assert(SemanticsMatcher.expectValue(SemanticsProperties.LiveRegion, LiveRegionMode.Polite))
    }

    private companion object {
        const val MESSAGE = "Couldn't save credential"
    }
}
