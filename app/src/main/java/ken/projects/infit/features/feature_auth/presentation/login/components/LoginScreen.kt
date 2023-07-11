package ken.projects.infit.features.feature_auth.presentation.login.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ken.projects.infit.R
import ken.projects.infit.core.components.InputField
import ken.projects.infit.core.components.LoadingView
import ken.projects.infit.features.feature_auth.presentation.login.events.authentication.LoginAuthEvent
import ken.projects.infit.features.feature_auth.presentation.login.events.button_click.LoginButtonEvent
import ken.projects.infit.features.feature_auth.presentation.login.events.user_input.LoginUserInputEvent
import ken.projects.infit.features.feature_auth.presentation.login.events.validation.LoginValidationEvent
import ken.projects.infit.ui.composables.RegularButton
import ken.projects.infit.ui.composables.home.Heading
import ken.projects.infit.ui.theme.*
import ken.projects.infit.features.feature_auth.presentation.login.viewmodel.LoginViewModel
import kotlinx.coroutines.flow.collect

@Composable
fun LoginScreen(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    scaffoldState: ScaffoldState
) = with(loginViewModel) {

    val context = LocalContext.current

    LaunchedEffect(key1 = state.error) {
        state.error?.let { message ->
            scaffoldState.snackbarHostState.showSnackbar(
                message.getString(context),
                null,
                SnackbarDuration.Short
            )
        }
    }
    LaunchedEffect(key1 = context) {
        navigationEvents.collect { route ->
            navController.navigate(route)
        }

    }

    LaunchedEffect(key1 = context) {
        loginEvents.collect { event ->

            when (event) {
                is LoginAuthEvent.Failure -> {
                    event.reason?.let {
                        scaffoldState.snackbarHostState.showSnackbar(
                            it.getString(context),
                            null,
                            SnackbarDuration.Short
                        )
                    }

                }
                is LoginAuthEvent.Success -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        "Success",
                        null,
                        SnackbarDuration.Short
                    )
                }
            }
        }

    }

    LaunchedEffect(key1 = context) {
        validationEvents.collect { event ->
            when (event) {
                LoginValidationEvent.Success -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        "success",
                        null,
                        SnackbarDuration.Short
                    )
                }
            }
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
            LoadingView(color = holoGreen)
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
                    input = state.email,
                    onValueChange = { onUserInputEvent(LoginUserInputEvent.EnteredEmail(it)) },
                    placeholder = stringResource(R.string.email),
                    icon = Icons.Rounded.Email,
                    type = KeyboardType.Email,
                )
                Spacer(modifier = Modifier.height(10.dp))

                InputField(
                    input = state.password,
                    onValueChange = { onUserInputEvent(LoginUserInputEvent.EnteredPassword(it)) },
                    placeholder = stringResource(R.string.password),
                    icon = Icons.Rounded.Lock,
                    type = KeyboardType.Password,
                    password = true,
                )
                SignUpSection(
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .fillMaxWidth()
                        .clickable { onButtonClickEvent(LoginButtonEvent.SignUpButtonClick) }
                )
                RegularButton(
                    Modifier
                        .padding(top = 20.dp)
                        .align(CenterHorizontally),
                    stringResource(R.string.login).lowercase(),
                    onClick = {
                        onButtonClickEvent(LoginButtonEvent.LoginButtonClick)
                    }
                )

            }
    }
}


