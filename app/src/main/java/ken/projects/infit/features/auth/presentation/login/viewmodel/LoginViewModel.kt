package ken.projects.infit.features.auth.presentation.login.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ken.projects.infit.features.auth.domain.use_case.AuthUseCases
import ken.projects.infit.features.auth.presentation.login.events.authentication.LoginAuthEvent
import ken.projects.infit.features.auth.presentation.login.events.button_click.LoginButtonEvent
import ken.projects.infit.features.auth.presentation.login.events.navigation.LoginNavigationEvent
import ken.projects.infit.features.auth.presentation.login.events.user_input.LoginUserInputEvent
import ken.projects.infit.features.auth.presentation.login.events.validation.LoginValidationEvent
import ken.projects.infit.features.auth.presentation.login.state.LoginState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCases: AuthUseCases
) : ViewModel() {


    var state by mutableStateOf(LoginState(email = "kenidiidali@gmail.com", password = "password123"))

    private val validationEventChannel = Channel<LoginValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    private val loginEventChannel = Channel<LoginAuthEvent>()
    val loginEvents = loginEventChannel.receiveAsFlow()

    private val navigationEvent = Channel<String>()
    val navigationEvents = navigationEvent.receiveAsFlow()


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
        viewModelScope.launch {
            navigationEvent.send(event.route)
        }
    }

//    fun onErrorEvent(event: LoginErrorEvent) {
//
//        state = when (event) {
//            is LoginErrorEvent.FailedLogin -> {
//                state.copy(
//                    data = null,
//                    loading = false,
//                    success = false,
//                    error = event.message
//                )
//            }
//            is LoginErrorEvent.InvalidField -> {
//                state.copy(
//                    error = "please enter valid " + event.field
//                )
//            }
//        }
//
//    }


    fun onButtonClickEvent(event: LoginButtonEvent) {
        viewModelScope.launch {
            when (event) {
                is LoginButtonEvent.ForgotPasswordButtonClick -> {
                }
                is LoginButtonEvent.LoginButtonClick -> {
                    submitData()
                }
                is LoginButtonEvent.SignUpButtonClick -> {
                    onNavigationEvent(LoginNavigationEvent.NavigateToSignup)
                }
            }
        }

    }

    private fun submitData() {

        val emailValidationResult = useCases.validateEmail.invoke(state.email)
        val passwordValidationResult = useCases.validatePassword.invoke(state.password)
        val hasError = listOf(
            emailValidationResult,
            passwordValidationResult
        ).any { !it.successful }

        state = state.copy(
            emailError = emailValidationResult.errorMessage,
            passwordError = passwordValidationResult.errorMessage
        )

        if (hasError) {
            return
        }

        viewModelScope.launch {
            validationEventChannel.send(LoginValidationEvent.Success)
        }

        loginUser()

    }

    private fun loginUser() {
        viewModelScope.launch {
            state = state.copy(loading = true)

            val result = useCases.loginUserWithEmailAndPassword.invoke(
                    state.email,
                    state.password
            )

            state = when (result.success) {
                true -> {
                    loginEventChannel.send(LoginAuthEvent.Success)
                    state.copy(loading = false)
                }
                false -> {
                    loginEventChannel.send(LoginAuthEvent.Failure(result.errorMessage))
                    state.copy(loading = false)
                }
            }
        }
    }


}