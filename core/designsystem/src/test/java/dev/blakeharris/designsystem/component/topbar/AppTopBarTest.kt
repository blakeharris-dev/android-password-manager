package dev.blakeharris.designsystem.component.topbar

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
class AppTopBarTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun `AppTopBar shows the app title`() {
        composeRule.setContent {
            AppTheme { AppTopBar() }
        }

        composeRule.onNodeWithText(TITLE).assertExists()
    }

    @Test
    fun `AppTopBar marks the title as a heading`() {
        composeRule.setContent {
            AppTheme { AppTopBar() }
        }

        composeRule.onNodeWithText(TITLE)
            .assert(SemanticsMatcher.keyIsDefined(SemanticsProperties.Heading))
    }

    private companion object {
        // Keep in step with the title string resource.
        const val TITLE = "Password Manager"
    }
}
