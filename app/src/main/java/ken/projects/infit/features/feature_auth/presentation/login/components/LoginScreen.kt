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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import ken.projects.infit.R
import ken.projects.infit.features.feature_auth.presentation.login.events.button_click.LoginButtonEvent
import ken.projects.infit.features.feature_auth.presentation.login.events.user_input.LoginUserInputEvent
import ken.projects.infit.ui.composables.RegularButton
import ken.projects.infit.ui.composables.home.Heading
import ken.projects.infit.ui.navigation.MAIN_ROUTE
import ken.projects.infit.ui.theme.*
import ken.projects.infit.features.feature_auth.presentation.login.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    scaffoldState: ScaffoldState
) = with(loginViewModel) {



    LaunchedEffect(key1 = state.error) {
        state.error?.let { message ->
            scaffoldState.snackbarHostState.showSnackbar(
                message,
                null,
                SnackbarDuration.Short
            )
        }

    }
    LaunchedEffect(key1 = state.navigateTo) {
        state.navigateTo?.let { destination ->
            navController.navigate(destination)

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
                    email,
                    onValueChange = { onUserInputEvent(LoginUserInputEvent.EnteredEmail(it)) },
                    placeholder = stringResource(R.string.email),
                    icon = Icons.Rounded.Email,
                    type = KeyboardType.Email
                )
                Spacer(modifier = Modifier.height(10.dp))

                InputField(
                    input = loginViewModel.password,
                    onValueChange = { onUserInputEvent(LoginUserInputEvent.EnteredPassword(it)) },
                    placeholder = stringResource(R.string.password),
                    icon = Icons.Rounded.Lock,
                    type = KeyboardType.Password,
                    true
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
                        loginViewModel.onButtonClickEvent(LoginButtonEvent.LoginButtonClick)
                    }
                )

            }
    }
}


