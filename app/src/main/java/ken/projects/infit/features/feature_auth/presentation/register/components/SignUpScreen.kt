package ken.projects.infit.features.feature_auth.presentation.register.components

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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ken.projects.infit.R
import ken.projects.infit.core.navigation.Screens
import ken.projects.infit.features.feature_auth.presentation.login.components.InputField
import ken.projects.infit.ui.composables.RegularButton
import ken.projects.infit.ui.composables.home.Heading
import ken.projects.infit.ui.theme.holoGreen
import ken.projects.infit.ui.theme.veryDarkBlue
import ken.projects.infit.features.feature_auth.presentation.login.viewmodel.LoginViewModel
import ken.projects.infit.features.feature_auth.presentation.register.events.button_click.SignUpButtonEvent
import ken.projects.infit.features.feature_auth.presentation.register.events.user_input.SignUpUserInputEvent
import ken.projects.infit.features.feature_auth.presentation.register.events.validation.SignUpValidationEvent
import ken.projects.infit.features.feature_auth.presentation.register.viewmodel.SignUpViewModel

@Composable
fun SignUpScreen(
    navController: NavHostController = rememberNavController(),
    signUpViewModel: SignUpViewModel = viewModel(),
    scaffoldState: ScaffoldState
) = with(signUpViewModel){

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
            navController.navigate(Screens.Login.route)
        }

    }

        Surface(
            color = veryDarkBlue.copy(0.6f),
            modifier = Modifier.paint(
                painterResource(id = R.drawable.register_background),
                contentScale = ContentScale.Crop,
            ).fillMaxSize(),
        ) {

            if (state.loading) {

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,

                    ) {
                    CircularProgressIndicator(
                        color = holoGreen.copy(0.6f),
                        strokeWidth = 5.dp,
                    )
                }

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
                    userName,
                    { onUserInputEvent(SignUpUserInputEvent.EnteredUserName(it)) },
                    stringResource(R.string.enter_name_hint),
                    Icons.Rounded.Person,
                    type = KeyboardType.Text,
                    onFocusChanged = {onValidationEvent(SignUpValidationEvent.UserNameValidation)},
                    isInvalid = userNameInvalid
                )
                InputField(
                    email,
                    { onUserInputEvent(SignUpUserInputEvent.EnteredEmail(it))},
                    stringResource(R.string.enter_email_hint),
                    Icons.Rounded.Email,
                    type = KeyboardType.Email,
                    onFocusChanged = {onValidationEvent(SignUpValidationEvent.EmailValidation)},
                    isInvalid = emailInvalid
                )

                InputField(
                    password,
                    { onUserInputEvent(SignUpUserInputEvent.EnteredPassword(it)) },
                    stringResource(R.string.enter_password_hint),
                    Icons.Rounded.Lock,
                    type = KeyboardType.Password,
                    true,
                    onFocusChanged = {onValidationEvent(SignUpValidationEvent.PasswordValidation)},
                    isInvalid = passwordInvalid
                )
                InputField(
                    confirmPassword,
                    { onUserInputEvent(SignUpUserInputEvent.EnteredConfirmPassword(it)) },
                    stringResource(R.string.confirm_password_hint),
                    Icons.Rounded.Lock,
                    type = KeyboardType.Password,
                    true,
                    onFocusChanged = {onValidationEvent(SignUpValidationEvent.ConfirmPasswordValidation)},
                    isInvalid = confirmPasswordInvalid
                )

                RegularButton(modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(top = 20.dp),
                    text = stringResource(R.string.continue_text),
                    onClick = {onButtonClickEvent(SignUpButtonEvent.SubmitButtonClick)}
                )


            }


        }


}