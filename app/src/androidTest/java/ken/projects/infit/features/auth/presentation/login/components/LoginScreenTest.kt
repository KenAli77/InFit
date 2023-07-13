package ken.projects.infit.features.auth.presentation.login.components

import androidx.activity.compose.setContent
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import ken.projects.infit.MainActivity
import ken.projects.infit.core.navigation.Screens
import ken.projects.infit.core.utils.TestTags.LOGIN_EMAIL_INPUT
import ken.projects.infit.core.utils.TestTags.LOGIN_PASSWORD_INPUT
import ken.projects.infit.features.auth.di.AuthModule
import ken.projects.infit.ui.theme.InFitTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AuthModule::class)
class LoginScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1 )
    val composeRule = createAndroidComposeRule<MainActivity>()
    
    @Before
    fun setUp(){
        hiltRule.inject()
        composeRule.activity.setContent {
            val navController = rememberNavController()
            
            InFitTheme {
                NavHost(navController = navController, startDestination = Screens.Login.route) {
                    composable(Screens.Login.route){
                        LoginScreen(navController = navController)
                    }
                }
            }
        }
    }

    @Test
    fun typeEmailInInputField_textIsEqualToInput(){
        composeRule.onNodeWithTag(LOGIN_EMAIL_INPUT).assertExists()
        composeRule.onNodeWithTag(LOGIN_EMAIL_INPUT).assert(hasText(""))
        composeRule.onNodeWithTag(LOGIN_EMAIL_INPUT).performTextInput("user@email.com")
        composeRule.onNodeWithTag(LOGIN_EMAIL_INPUT).assert(hasText("user@email.com"))
    }

    @Test
    fun typePasswordInInputField_textIsEqualToInput(){
        val password = "password123"
        composeRule.onNodeWithTag(LOGIN_PASSWORD_INPUT).assertExists()
        composeRule.onNodeWithTag(LOGIN_PASSWORD_INPUT).assert(hasText(""))
        composeRule.onNodeWithTag(LOGIN_PASSWORD_INPUT).performTextInput(password)
        composeRule.onNodeWithTag(LOGIN_PASSWORD_INPUT).assert(!hasText(""))
    }
    
}