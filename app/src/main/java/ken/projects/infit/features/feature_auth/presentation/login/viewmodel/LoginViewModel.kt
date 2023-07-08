package ken.projects.infit.features.feature_auth.presentation.login.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthResult
import dagger.hilt.android.lifecycle.HiltViewModel
import ken.projects.infit.features.feature_auth.data.models.EmailLogin
import ken.projects.infit.features.feature_auth.domain.use_case.AuthUseCases
import ken.projects.infit.features.feature_auth.presentation.login.events.button_click.LoginButtonEvent
import ken.projects.infit.features.feature_auth.presentation.login.events.error.LoginErrorEvent
import ken.projects.infit.features.feature_auth.presentation.login.events.user_input.LoginUserInputEvent
import ken.projects.infit.features.feature_auth.presentation.login.state.LoginState
import ken.projects.infit.ui.navigation.MAIN_ROUTE
import ken.projects.infit.ui.navigation.Screens
import ken.projects.infit.util.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCases: AuthUseCases
) : ViewModel() {

    var email by mutableStateOf("")

    var password by mutableStateOf("")

    var state by mutableStateOf(LoginState())

//    fun onValidationEvent(event: LoginValidationEvent) {}


    fun onUserInputEvent(event: LoginUserInputEvent) {
        when (event) {
            is LoginUserInputEvent.EnteredEmail -> {
                email = event.email
            }
            is LoginUserInputEvent.EnteredPassword -> {
                password = event.password
            }
        }
    }

    fun onErrorEvent(event: LoginErrorEvent) {

        state = when (event) {
            is LoginErrorEvent.FailedLogin -> {
                state.copy(
                    data = null,
                    loading = false,
                    success = false,
                    error = event.message
                )
            }
            is LoginErrorEvent.InvalidField -> {
                state.copy(
                    error = "please enter valid " + event.field
                )
            }
        }

    }


    fun onButtonClickEvent(event: LoginButtonEvent) {
        viewModelScope.launch {
            when (event) {
                is LoginButtonEvent.ForgotPasswordButtonClick -> {
                }
                is LoginButtonEvent.LoginButtonClick -> {
                    if (!useCases.validateEmail.invoke(email = email)) {
                        onErrorEvent(event = LoginErrorEvent.InvalidField("email"))
                    } else if (password.isBlank()) {
                        onErrorEvent(event = LoginErrorEvent.InvalidField("password"))
                    } else {
                        val result = useCases.loginUserWithEmailAndPassword.invoke(
                            EmailLogin(
                                email = email,
                                password = password
                            )
                        )

                        authenticateUser(result)
                    }
                }
                is LoginButtonEvent.SignUpButtonClick -> {
                    state = state.copy(
                        navigateTo = Screens.Signup.route
                    )
                }
            }
        }

    }

    private fun authenticateUser(response: Resource<AuthResult>) {

        when (response) {
            is Resource.Error -> {
                response.message?.let {
                    onErrorEvent(LoginErrorEvent.FailedLogin(it))
                } ?: onErrorEvent(LoginErrorEvent.FailedLogin("Unknown error"))


            }
            is Resource.Loading -> {
                state = state.copy(
                    data = null,
                    loading = true,
                    success = false,
                    error = null,
                    navigateTo = null
                )
            }
            is Resource.Success -> {
                state = state.copy(
                    data = response.data,
                    loading = false,
                    success = true,
                    error = null,
                    navigateTo = MAIN_ROUTE
                )
            }
        }

    }

}