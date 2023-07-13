package ken.projects.infit.core.components

import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.focus.FocusState
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import ken.projects.infit.core.utils.TestTags
import ken.projects.infit.ui.theme.white

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
    isInvalid:Boolean = false,
    errorMessage:String? = null,
    tag:String = ""
    ) {

    Column(modifier = modifier) {

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
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { onFocusChanged(it) }
                .testTag(tag),
            maxLines = 1,
            keyboardOptions = KeyboardOptions(
                keyboardType = type,
                imeAction = ImeAction.Done
            ),
            visualTransformation = if (password) PasswordVisualTransformation() else {
                VisualTransformation.None
            },
            isError = isInvalid,
        )

        errorMessage?.let {
            Text(text = it, color = Color.Red)
        }
    }

}
