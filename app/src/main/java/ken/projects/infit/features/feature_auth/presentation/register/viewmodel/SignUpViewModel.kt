package ken.projects.infit.features.feature_auth.presentation.register.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ken.projects.infit.features.feature_auth.data.models.NewUser
import ken.projects.infit.features.feature_auth.domain.use_case.AuthUseCases
import ken.projects.infit.features.feature_auth.presentation.register.events.button_click.SignUpButtonEvent
import ken.projects.infit.features.feature_auth.presentation.register.events.error.SignupErrorEvent
import ken.projects.infit.features.feature_auth.presentation.register.events.user_input.SignUpUserInputEvent
import ken.projects.infit.features.feature_auth.presentation.register.events.validation.SignUpValidationEvent
import ken.projects.infit.features.feature_auth.presentation.register.state.SignupState
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val useCases: AuthUseCases
) : ViewModel() {

    var userName by mutableStateOf("")
        private set

    var userNameInvalid by mutableStateOf(false)
        private set

    var email by mutableStateOf("")
        private set
    var emailInvalid by mutableStateOf(false)
        private set

    var password by mutableStateOf("")
        private set
    var passwordInvalid by mutableStateOf(false)
        private set

    var confirmPassword by mutableStateOf("")
        private set
    var confirmPasswordInvalid by mutableStateOf(false)
        private set

    var state by mutableStateOf(SignupState())

    fun onUserInputEvent(event: SignUpUserInputEvent) {

        when (event) {
            is SignUpUserInputEvent.EnteredConfirmPassword -> {
                confirmPassword = event.confirmPassword
            }
            is SignUpUserInputEvent.EnteredEmail -> {
                email = event.email
            }
            is SignUpUserInputEvent.EnteredPassword -> {
                password = event.password
            }
            is SignUpUserInputEvent.EnteredUserName -> {
                userName = event.userName
            }
        }
    }

    fun onButtonClickEvent(event: SignUpButtonEvent) {
        when (event) {
            SignUpButtonEvent.SubmitButtonClick -> {
                val user = NewUser(
                    userEmail = email,
                    userName = userName,
                    password = password,
                    confirmPassword = confirmPassword
                )
                validateAllFields()
                if (state.error.isNullOrBlank()) {
                    viewModelScope.launch {
                        useCases.createNewUser.invoke(user)
                    }
                }
            }
        }
    }

    fun onValidationEvent(event: SignUpValidationEvent) {

        val userData = NewUser(
            userEmail = email,
            userName = userName,
            password = password,
            confirmPassword = confirmPassword
        )

        viewModelScope.launch {
            when (event) {
                SignUpValidationEvent.ConfirmPasswordValidation -> {
                    val result = useCases.arePasswordMatching.invoke(userData)
                    if (!result) {
                        confirmPasswordInvalid = true
                        onErrorEvent(event = SignupErrorEvent.PasswordsNotMatching)
                    } else {
                        confirmPasswordInvalid = false
                    }

                }
                SignUpValidationEvent.EmailValidation -> {
                    val result = useCases.validateEmail.invoke(userData.userEmail)
                    if (!result) {
                        emailInvalid = true
                        onErrorEvent(event = SignupErrorEvent.InvalidPassword)
                    } else {
                        emailInvalid = false
                    }
                }
                SignUpValidationEvent.PasswordValidation -> {
                    /**
                     * TODO
                     * add password validation logic
                     */
                }
                SignUpValidationEvent.UserNameValidation -> {
                    val result = useCases.validateUserName.invoke(userName)
                    if (!result) {
                        onErrorEvent(SignupErrorEvent.InvalidUserName)
                        userNameInvalid = true
                    } else {
                        userNameInvalid = false
                    }
                }
            }
        }

    }

    fun onErrorEvent(event: SignupErrorEvent) {
        when (event) {
            is SignupErrorEvent.FailedSignUp -> {}
            is SignupErrorEvent.InvalidEmail -> {
                state = state.copy(
                    error = "Invalid email"
                )
            }
            is SignupErrorEvent.InvalidPassword -> {
                state = state.copy(
                    error = "Password not valid"
                )
            }
            is SignupErrorEvent.PasswordsNotMatching -> {
                state = state.copy(
                    error = "Passowrds not matching"
                )
            }
            SignupErrorEvent.InvalidUserName -> {
                state = state.copy(
                    error = "Please enter a valid username"
                )
            }
        }
    }

    fun validateAllFields() {
        onValidationEvent(SignUpValidationEvent.UserNameValidation)
        onValidationEvent(SignUpValidationEvent.EmailValidation)
        onValidationEvent(SignUpValidationEvent.PasswordValidation)
        onValidationEvent(SignUpValidationEvent.ConfirmPasswordValidation)
    }

}