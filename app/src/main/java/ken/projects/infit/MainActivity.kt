package ken.projects.infit

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ken.projects.infit.data.service.WorkoutTimerService
import ken.projects.infit.core.navigation.RootNavGraph
import ken.projects.infit.ui.theme.InFitTheme
import ken.projects.infit.util.getTimeStringFromDouble
import ken.projects.infit.features.feature_auth.presentation.login.viewmodel.LoginViewModel
import ken.projects.infit.viewmodel.WorkoutViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

//    private val loginViewModel: LoginViewModel by viewModels()
//    private val workoutViewModel: WorkoutViewModel by viewModels()
//    private var timeElapsed = 0.0
//    private lateinit var serviceIntent: Intent


//    private val updateTime: BroadcastReceiver = object : BroadcastReceiver() {
//
//        override fun onReceive(context: Context, intent: Intent) {
//            timeElapsed = intent.getDoubleExtra(WorkoutTimerService.TIME_ELAPSED, 0.0)
//            workoutViewModel.timerText = getTimeStringFromDouble(timeElapsed)
//        }
//
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//
//        serviceIntent = Intent(this, WorkoutTimerService::class.java)
//        registerReceiver(
//            updateTime,
//            IntentFilter(WorkoutTimerService.TIMER_UPDATED)
//        )
        setContent {

            InFitTheme {

                val workoutViewModel = hiltViewModel<WorkoutViewModel>()
                val loginViewModel = hiltViewModel<LoginViewModel>()

                val navController = rememberNavController()
                RootNavGraph(navController,loginViewModel,workoutViewModel)

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
//        stopService(serviceIntent)
    }
}


