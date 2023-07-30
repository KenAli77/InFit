package ken.projects.infit.core.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.sp
import ken.projects.infit.core.utils.customClickable
import ken.projects.infit.ui.theme.holoGreen

@Composable
fun InputField(
    modifier: Modifier = Modifier,
    input: String = "",
    onValueChange: (String) -> Unit,
    placeholder: String = "email",
    icon: ImageVector = Icons.Rounded.Email,
    type: KeyboardType = KeyboardType.Email,
    password: Boolean = false,
    onFocusChanged: (FocusState) -> Unit = {},
    isInvalid: Boolean = false,
    errorMessage: String? = null,
    tag: String = "",
) {
    var isPassVisible by remember { mutableStateOf(false) }
    Column(modifier = modifier) {
        OutlinedTextField(
            value = input,
            onValueChange = onValueChange,
            leadingIcon = {
                Icon(
                    imageVector = icon,
                    contentDescription = null,

                    )
            },
            label = { Text(text = placeholder) },
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { onFocusChanged(it) }
                .testTag(tag),
            maxLines = 1,
            keyboardOptions = KeyboardOptions(
                keyboardType = type,
                imeAction = ImeAction.Done
            ),
            visualTransformation = if (password && !isPassVisible) {
                PasswordVisualTransformation()
            } else {
                VisualTransformation.None
            },
            isError = isInvalid,
            trailingIcon = {
                if (password) {
                    Icon(
                        imageVector = if (isPassVisible) {
                            Icons.Rounded.VisibilityOff
                        } else {
                            Icons.Rounded.Visibility
                        },
                        contentDescription = null,
                        modifier = Modifier.customClickable {
                            isPassVisible = !isPassVisible
                        }
                    )
                }
            },
        )
        errorMessage?.let {
            Text(text = it, color = Color.Red)
        }
    }
}

@Composable
fun InputText(
    value: String = "",
    onValueChange: (String) -> Unit = {},
    modifier: Modifier = Modifier,
    placeholder: String = ""
) {
    OutlinedTextField(
        modifier = modifier,
        value = value,
        onValueChange = {
            onValueChange(it)
        },
        textStyle = TextStyle(fontSize = 18.sp),
        colors = TextFieldDefaults.textFieldColors(cursorColor = holoGreen),
        placeholder = { Text(text = placeholder) },
        maxLines = 1,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),

        )
}
