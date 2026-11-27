package dev.blakeharris.designsystem.component.state

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import dev.blakeharris.designsystem.icon.AppIcons

@Composable
internal fun StatusContent(icon: ImageVector, iconTint: Color, text: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = iconTint,
            modifier = Modifier.size(48.dp)
        )
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
fun ErrorContent(text: String, modifier: Modifier = Modifier) =
    StatusContent(AppIcons.Error, MaterialTheme.colorScheme.error, text, modifier)

@Composable
fun InfoContent(text: String, modifier: Modifier = Modifier) =
    StatusContent(AppIcons.Info, MaterialTheme.colorScheme.error, text, modifier)

@Composable
fun SuccessContent(text: String, modifier: Modifier = Modifier) =
    StatusContent(AppIcons.Success, MaterialTheme.colorScheme.error, text, modifier)
