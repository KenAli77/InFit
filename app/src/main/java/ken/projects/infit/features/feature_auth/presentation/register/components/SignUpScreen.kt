package ken.projects.infit.features.feature_auth.presentation.register.components

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Lock
import androidx.compose.material.icons.rounded.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ken.projects.infit.R
import ken.projects.infit.core.navigation.Screens
import ken.projects.infit.core.components.InputField
import ken.projects.infit.core.components.LoadingView
import ken.projects.infit.ui.composables.RegularButton
import ken.projects.infit.ui.composables.home.Heading
import ken.projects.infit.ui.theme.holoGreen
import ken.projects.infit.ui.theme.veryDarkBlue
import ken.projects.infit.features.feature_auth.presentation.register.events.authentication.SignUpAuthEvent
import ken.projects.infit.features.feature_auth.presentation.register.events.button_click.SignUpButtonEvent
import ken.projects.infit.features.feature_auth.presentation.register.events.user_input.SignUpUserInputEvent
import ken.projects.infit.features.feature_auth.presentation.register.events.validation.SignUpValidationEvent
import ken.projects.infit.features.feature_auth.presentation.register.viewmodel.SignUpViewModel

@Composable
fun SignUpScreen(
    navController: NavHostController = rememberNavController(),
    signUpViewModel: SignUpViewModel = viewModel(),
    scaffoldState: ScaffoldState
) = with(signUpViewModel) {

    BackHandler() {
        Log.e("navigating back","signup screen")
        navController.navigateUp()
        navController.popBackStack()
    }

    val context = LocalContext.current
    LaunchedEffect(key1 = state.error) {
        state.error?.let {
            scaffoldState.snackbarHostState.showSnackbar(
                it,
                null,
                SnackbarDuration.Short
            )
        }
    }

    LaunchedEffect(key1 = context) {
        validationEvents.collect { event ->
            when (event) {
                is SignUpValidationEvent.Success -> {
                    /**
                     * TODO: handle validation success
                     */
                }
            }
        }
    }

    LaunchedEffect(key1 = context) {
        registrationEvents.collect { event ->
            when (event) {
                is SignUpAuthEvent.Failure -> {
                    event.message?.let {
                        scaffoldState.snackbarHostState.showSnackbar(
                            it,
                            null,
                            SnackbarDuration.Short
                        )
                    }

                }
                is SignUpAuthEvent.Success -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        "Your account has been created!",
                        null,
                        SnackbarDuration.Short
                    )
                }
            }
        }
    }

    Surface(
        color = veryDarkBlue.copy(0.6f),
        modifier = Modifier
            .paint(
                painterResource(id = R.drawable.register_background),
                contentScale = ContentScale.Crop,
            )
            .fillMaxSize(),
    ) {
        if (state.loading) {
            LoadingView(color = holoGreen)
        } else

            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp, Alignment.CenterVertically),
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(horizontal = 50.dp)
            ) {
                Heading(
                    text = stringResource(R.string.signup), modifier = Modifier
                        .align(Alignment.Start)
                        .padding(bottom = 20.dp)
                        .fillMaxWidth()
                )
                InputField(
                    input = state.userName,
                    onValueChange = { onUserInputEvent(SignUpUserInputEvent.EnteredUserName(it)) },
                    placeholder = stringResource(R.string.enter_name_hint),
                    icon = Icons.Rounded.Person,
                    type = KeyboardType.Text,
                    onFocusChanged = { },
                    isInvalid = state.userNameError != null,
                    errorMessage = state.userNameError
                )
                InputField(
                    input = state.email,
                    onValueChange = { onUserInputEvent(SignUpUserInputEvent.EnteredEmail(it)) },
                    placeholder = stringResource(R.string.enter_email_hint),
                    icon = Icons.Rounded.Email,
                    type = KeyboardType.Email,
                    onFocusChanged = { },
                    isInvalid = state.emailError != null,
                    errorMessage = state.emailError
                )
                InputField(
                    input = state.password,
                    onValueChange = { onUserInputEvent(SignUpUserInputEvent.EnteredPassword(it)) },
                    placeholder = stringResource(R.string.enter_password_hint),
                    icon = Icons.Rounded.Lock,
                    type = KeyboardType.Password,
                    password = true,
                    onFocusChanged = { },
                    isInvalid = state.passwordError != null,
                    errorMessage = state.passwordError,
                )
                InputField(
                    input = state.confirmPassword,
                    onValueChange = {
                        onUserInputEvent(
                            SignUpUserInputEvent.EnteredConfirmPassword(
                                it
                            )
                        )
                    },
                    placeholder = stringResource(R.string.confirm_password_hint),
                    icon = Icons.Rounded.Lock,
                    type = KeyboardType.Password,
                    password = true,
                    onFocusChanged = { },
                    isInvalid = state.confirmPasswordError != null,
                    errorMessage = state.confirmPasswordError,
                )
                RegularButton(modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 20.dp),
                    text = stringResource(R.string.continue_text),
                    onClick = { onButtonClickEvent(SignUpButtonEvent.SubmitButtonClick) }
                )
            }
    }
}