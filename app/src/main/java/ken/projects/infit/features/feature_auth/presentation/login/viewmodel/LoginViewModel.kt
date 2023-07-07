package ken.projects.infit.features.feature_auth.presentation.login.viewmodel

import android.app.Application
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ken.projects.infit.data.models.User
import ken.projects.infit.data.models.states.AuthState
import ken.projects.infit.features.feature_auth.data.models.EmailLogin
import ken.projects.infit.features.feature_auth.domain.repostitories.AuthRepository
import ken.projects.infit.features.feature_auth.domain.use_case.AuthUseCases
import ken.projects.infit.features.feature_auth.presentation.login.events.button_click.LoginButtonEvent
import ken.projects.infit.features.feature_auth.presentation.login.events.error.LoginErrorEvent
import ken.projects.infit.features.feature_auth.presentation.login.events.user_input.LoginUserInputEvent
import ken.projects.infit.features.feature_auth.presentation.login.events.validation.LoginValidationEvent
import ken.projects.infit.util.Resource
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class LoginViewModel @Inject constructor(
    private val useCases: AuthUseCases
) : ViewModel() {

    var signUpState by mutableStateOf(AuthState())
        private set

    var signInState by mutableStateOf(AuthState())
        private set

    var user by mutableStateOf(User())
        private set

    var eMail by mutableStateOf("")

    var password by mutableStateOf("")


    //    fun onButtonClickEvent(event:LoginButtonEvent){}
    fun onUserInputEvent(event: LoginUserInputEvent) {}
    fun onErrorEvent(event: LoginErrorEvent) {}
    fun onValidationEvent(event: LoginValidationEvent) {}


    fun onButtonClickEvent(event: LoginButtonEvent) {
        viewModelScope.launch {
            when (event) {
                LoginButtonEvent.ForgotPasswordButtonClick -> TODO()
                LoginButtonEvent.SignInButtonClick -> {
                    if (!useCases.validateEmail.invoke(email = eMail)) {
                        onErrorEvent(event = LoginErrorEvent())
                    } else {
                        useCases.loginUserWithEmailAndPassword.invoke(
                            EmailLogin(
                                email = eMail,
                                password = password
                            )
                        )
                    }
                }
                LoginButtonEvent.SignUpButtonClick -> {

                }
            }
        }

    }


    fun signUpUser(
        userName: String,
        userEmail: String,
        userPassword: String,
        confirmPassword: String
    ) =
        viewModelScope.launch {

            val arePasswordMatching = userPassword == confirmPassword

            if (arePasswordMatching) {

                signUpState = signUpState.copy(
                    loading = true,
                    error = null,

                    success = false
                )

                val result = repository.createNewUser(
                    userName = userName,
                    userEmailAddress = userEmail,
                    userLoginPassword = userPassword
                )

                when (result) {
                    is Resource.Success -> {
                        signUpState = signUpState.copy(
                            data = result.data,
                            loading = false,
                            success = true,
                            error = null
                        )
                    }

                    is Resource.Error -> {
                        signUpState = signUpState.copy(
                            data = null,
                            loading = false,
                            success = false,
                            error = result.message.toString()
                        )
                    }

                    is Resource.Loading -> {
                        signUpState = signUpState.copy(
                            data = null,
                            loading = true,
                            success = false,
                            error = null
                        )
                    }
                }
            } else {
                signUpState = signUpState.copy(
                    error = "passwords are not matching"
                )
            }

        }

    fun signInUser(userEmail: String, userPassword: String) =

        viewModelScope.launch {

            signInState = signInState.copy(
                loading = true
            )

            val result = repository.loginUser(email = userEmail, password = userPassword)

            signInState = when (result) {
                is Resource.Success -> {
                    signInState.copy(
                        loading = false,
                        success = true,
                        uid = result.data?.user?.uid
                    )

                }

                is Resource.Loading -> {
                    signInState.copy(
                        loading = true,
                        success = false,
                    )
                }

                is Resource.Error -> {
                    signInState.copy(
                        loading = false,
                        success = false,
                        error = result.message
                    )
                }
            }
        }

    fun logOut() = viewModelScope.launch {

        repository.logOutUser()

        signInState = AuthState()
        signUpState = AuthState()

    }


}