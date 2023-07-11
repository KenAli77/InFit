package ken.projects.infit.features.feature_auth.presentation.login.components

import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import ken.projects.infit.MainActivity
import ken.projects.infit.core.navigation.Screens
import ken.projects.infit.core.utils.TestTags.LOGIN_BUTTON
import ken.projects.infit.core.utils.TestTags.LOGIN_EMAIL_INPUT
import ken.projects.infit.features.feature_auth.di.AuthModule
import ken.projects.infit.ui.theme.InFitTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(AuthModule::class)
class LoginScreenTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeRule = createAndroidComposeRule<MainActivity>()
    
    @Before
    fun setUp(){
        hiltRule.inject()
        composeRule.setContent { 
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
        composeRule.onNodeWithTag(LOGIN_EMAIL_INPUT).assertTextEquals("")
        composeRule.onNodeWithTag(LOGIN_EMAIL_INPUT).performTextInput("user@email.com")
        composeRule.onNodeWithTag(LOGIN_EMAIL_INPUT).assertTextEquals("user@email.com")
    }
    
}