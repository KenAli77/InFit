package ken.projects.infit.features.feature_auth.presentation.login.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import ken.projects.infit.ui.theme.white

@Composable
fun InputField(
    input: String = "",
    onValueChange: (String) -> Unit,
    placeholder: String = "email",
    icon: ImageVector = Icons.Rounded.Email,
    type: KeyboardType = KeyboardType.Email,
    password: Boolean = false
) {

    TextField(
        value = input,
        onValueChange = onValueChange,
        leadingIcon = {
            Icon(
                imageVector = icon,
                contentDescription = null,

                )

        },
        colors = TextFieldDefaults.textFieldColors(backgroundColor = white, textColor = Color.Black),
        label = { Text(text = placeholder) },
        modifier = Modifier.fillMaxWidth(),
        maxLines = 1,
        keyboardOptions = KeyboardOptions(
            keyboardType = type,
            imeAction = ImeAction.Done
        ),
        visualTransformation = if (password) PasswordVisualTransformation() else {
            VisualTransformation.None
        }

    )

}
