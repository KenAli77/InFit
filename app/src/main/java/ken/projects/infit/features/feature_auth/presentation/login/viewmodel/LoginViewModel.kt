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
import ken.projects.infit.features.feature_auth.presentation.login.events.authentication.LoginAuthEvent
import ken.projects.infit.features.feature_auth.presentation.login.events.button_click.LoginButtonEvent
import ken.projects.infit.features.feature_auth.presentation.login.events.error.LoginErrorEvent
import ken.projects.infit.features.feature_auth.presentation.login.events.navigation.LoginNavigationEvent
import ken.projects.infit.features.feature_auth.presentation.login.events.user_input.LoginUserInputEvent
import ken.projects.infit.features.feature_auth.presentation.login.state.LoginState
import ken.projects.infit.features.feature_auth.presentation.register.events.authentication.SignUpAuthEvent
import ken.projects.infit.features.feature_auth.presentation.register.events.validation.SignUpValidationEvent
import ken.projects.infit.util.Resource
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCases: AuthUseCases
) : ViewModel() {


    var state by mutableStateOf(LoginState())

    private val validationEventChannel = Channel<SignUpValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    private val loginEventChannel = Channel<LoginAuthEvent>()
    val loginEvents = loginEventChannel.receiveAsFlow()


    fun onUserInputEvent(event: LoginUserInputEvent) {
        when (event) {
            is LoginUserInputEvent.EnteredEmail -> {
                state = state.copy(email = event.email)
            }
            is LoginUserInputEvent.EnteredPassword -> {
                state = state.copy(password = event.password)
            }
        }
    }

    fun onNavigationEvent(event: LoginNavigationEvent) {
        state = state.copy(navigateTo = event.route)
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
                    submitData()
                    if (!useCases.validateEmail.invoke(email = state.email).successful) {
                        onErrorEvent(event = LoginErrorEvent.InvalidField("email"))
                    } else if (state.password.isNullOrBlank()) {
                        onErrorEvent(event = LoginErrorEvent.InvalidField("password"))
                    } else {
                        val result = useCases.loginUserWithEmailAndPassword.invoke(
                            EmailLogin(
                                email = state.email!!,
                                password = state.password!!
                            )
                        )

                        authenticateUser(result)
                    }
                }
                is LoginButtonEvent.SignUpButtonClick -> {
                    onNavigationEvent(LoginNavigationEvent.NavigateToSignup)
                }
            }
        }

    }

    private fun submitData() {

        val emailValidationResult = useCases.validateEmail.invoke(state.email).successful
        val passwordValidationResult = password.isNotBlank()
        val hasError = listOf(
            emailValidationResult,
            passwordValidationResult
        ).any { false }

        state = state.copy(
            emailError = emailValidationResult.errorMessage,
        )

        if (hasError) {
            return
        }

        viewModelScope.launch {
            validationEventChannel.send(SignUpValidationEvent.Success)
        }

        loginUser()


    }

    private fun loginUser() {
        viewModelScope.launch {
            state = state.copy(loading = true)

            val result = useCases.loginUserWithEmailAndPassword.invoke(
                EmailLogin(
                    state.email,
                    state.password
                )
            )

            when (result.success) {
                true -> {
                    loginEventChannel.send(LoginAuthEvent.Success)
                    state = state.copy(loading = false)
                }
                false -> {
                    loginEventChannel.send(LoginAuthEvent.Failure(result.errorMessage))
                    state = state.copy(loading = false)
                }
            }
        }
    }


}