package dev.blakeharris.designsystem.component.input

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import dev.blakeharris.designsystem.R
import dev.blakeharris.designsystem.icon.AppIcons

@Composable
fun SecretField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
    optional: Boolean = false,
    keyboardType: KeyboardType = KeyboardType.Password,
    readOnly: Boolean = false
) {
    var revealed by rememberSaveable { mutableStateOf(false) }

    AppTextField(
        value = value,
        onValueChange = onValueChange,
        label = label,
        modifier = modifier,
        readOnly = readOnly,
        optional = optional,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        visualTransformation = if (revealed) {
            VisualTransformation.None
        } else {
            PasswordVisualTransformation()
        },
        trailingIcon = {
            RevealIcon(
                onClick = { revealed = !revealed },
                revealed = revealed
            )
        }
    )
}

@Composable
private fun RevealIcon(onClick: () -> Unit, revealed: Boolean) {
    IconButton(onClick = onClick) {
        Icon(
            imageVector = if (revealed) AppIcons.VisibilityOff else AppIcons.Visibility,
            contentDescription = stringResource(
                if (revealed) {
                    R.string.designsystem_input_hide_secret
                } else {
                    R.string.designsystem_input_show_secret
                }
            )
        )
    }
}
