package ken.projects.infit.ui.composables.login

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.TextFieldDefaults.textFieldColors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ken.projects.infit.R
import ken.projects.infit.ui.composables.RegularButton
import ken.projects.infit.ui.composables.home.Heading
import ken.projects.infit.ui.navigation.MAIN_ROUTE
import ken.projects.infit.ui.navigation.Screens
import ken.projects.infit.ui.theme.*
import ken.projects.infit.viewmodel.UserViewModel

@Composable
fun LoginScreen(
    navController: NavHostController,
    userViewModel: UserViewModel,
    scaffoldState: ScaffoldState
) {

    val state = userViewModel.signInState

    var eMail by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    LaunchedEffect(key1 = state.error) {
        state.error?.let {
            scaffoldState.snackbarHostState.showSnackbar(
                it,
                null,
                SnackbarDuration.Short
            )
        }

    }
    LaunchedEffect(key1 = state.success) {
        if (state.success) {
            navController.navigate(MAIN_ROUTE)
        }
    }


    Surface(
        color = veryDarkBlue.copy(0.7f),
        modifier = Modifier
            .paint(
                painterResource(id = R.drawable.login_background),
                contentScale = ContentScale.Crop,
            )
            .fillMaxSize(),
    )
    {


        if (state.loading) {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = CenterHorizontally,

                ) {
                CircularProgressIndicator(
                    color = holoGreen,
                    strokeWidth = 5.dp,
                )
            }

        } else
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 50.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start,

                ) {

                Heading(
                    text = stringResource(R.string.login), modifier = Modifier
                        .align(Alignment.Start)
                        .padding(bottom = 20.dp)
                        .fillMaxWidth()
                )
                InputField(
                    eMail,
                    onValueChange = { eMail = it },
                    placeholder = stringResource(R.string.email),
                    icon = Icons.Rounded.Email,
                    type = KeyboardType.Email
                )
                Spacer(modifier = Modifier.height(10.dp))

                InputField(
                    input = password,
                    onValueChange = { password = it },
                    placeholder = stringResource(R.string.password),
                    icon = Icons.Rounded.Lock,
                    type = KeyboardType.Password,
                    true
                )
                SignUpRow(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth()
                        .clickable { navController.navigate(Screens.Signup.route) }
                )
                RegularButton(
                    Modifier
                        .padding(top = 20.dp)
                        .align(CenterHorizontally),
                    stringResource(R.string.login).lowercase(),
                    onClick = {
                        userViewModel.signInUser(
                            userEmail = eMail,
                            userPassword = password
                        )

                        eMail = ""
                        password = ""


                    }
                )


            }


    }
}


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
        colors = textFieldColors(backgroundColor = white, textColor = Color.Black),
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

@Composable
fun SignUpRow(modifier: Modifier = Modifier) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {

        Text(
            text = stringResource(R.string.sign_up_text),
            fontWeight = FontWeight.Normal,
            color = white,
            fontFamily = outfit
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = stringResource(R.string.sign_up),
            fontWeight = FontWeight.Bold,
            color = holoGreen,
            fontFamily = outfit
        )
    }
}