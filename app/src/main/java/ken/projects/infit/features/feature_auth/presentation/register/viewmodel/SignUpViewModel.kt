package ken.projects.infit.features.feature_auth.presentation.register.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ken.projects.infit.features.feature_auth.domain.use_case.AuthUseCases
import ken.projects.infit.features.feature_auth.presentation.register.events.authentication.SignUpAuthEvent
import ken.projects.infit.features.feature_auth.presentation.register.events.button_click.SignUpButtonEvent
import ken.projects.infit.features.feature_auth.presentation.register.events.user_input.SignUpUserInputEvent
import ken.projects.infit.features.feature_auth.presentation.register.events.validation.SignUpValidationEvent
import ken.projects.infit.features.feature_auth.presentation.register.state.SignupState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val useCases: AuthUseCases
) : ViewModel() {

    var state by mutableStateOf(SignupState())

    private val validationEventChannel = Channel<SignUpValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    private val registrationEventChannel = Channel<SignUpAuthEvent>()
    val registrationEvents = registrationEventChannel.receiveAsFlow()


    fun onUserInputEvent(event: SignUpUserInputEvent) {

        state = when (event) {
            is SignUpUserInputEvent.EnteredConfirmPassword -> {
                state.copy(confirmPassword = event.confirmPassword)
            }
            is SignUpUserInputEvent.EnteredEmail -> {
                state.copy(email = event.email)
            }
            is SignUpUserInputEvent.EnteredPassword -> {
                state.copy(password = event.password)
            }
            is SignUpUserInputEvent.EnteredUserName -> {
                state.copy(userName = event.userName)
            }
        }
    }

    fun onButtonClickEvent(event: SignUpButtonEvent) {
        when (event) {
            SignUpButtonEvent.SubmitButtonClick -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val emailValidationResult = useCases.validateEmail.invoke(state.email)
        val userNameValidationResult = useCases.validateUserName.invoke(state.userName)
        val passwordValidationResult = useCases.validatePassword.invoke(state.password)
        val confirmPasswordValidationResult = useCases.arePasswordMatching.invoke(state.password,state.confirmPassword)

        val hasError = listOf(
            emailValidationResult,
            userNameValidationResult,
            passwordValidationResult,
            confirmPasswordValidationResult
        ).any { !it.successful }

        state = state.copy(
            emailError = emailValidationResult.errorMessage,
            userNameError = userNameValidationResult.errorMessage,
            passwordError = passwordValidationResult.errorMessage,
            confirmPasswordError = confirmPasswordValidationResult.errorMessage
        )

        if (hasError) {
            return
        }

        viewModelScope.launch {
            validationEventChannel.send(SignUpValidationEvent.Success)
        }

        registerUser()
    }

    private fun registerUser() {
        viewModelScope.launch {
            state = state.copy(loading = true)
            val authResult =
                useCases.createNewUser.invoke(state.userName, state.email, state.password)

            when (authResult.success) {
                true -> {
                    registrationEventChannel.send(SignUpAuthEvent.Success)
                    state = state.copy(loading = false)
                }
                false -> {
                    registrationEventChannel.send(SignUpAuthEvent.Failure(authResult.errorMessage))
                    state = state.copy(loading = false)
                }
            }
        }
    }

}