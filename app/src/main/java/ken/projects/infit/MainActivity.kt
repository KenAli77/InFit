package ken.projects.infit

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ken.projects.infit.data.service.WorkoutTimerService
import ken.projects.infit.ui.navigation.RootNavGraph
import ken.projects.infit.ui.theme.InFitTheme
import ken.projects.infit.util.getTimeStringFromDouble
import ken.projects.infit.viewmodel.UserViewModel
import ken.projects.infit.viewmodel.WorkoutViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    lateinit var navController:NavHostController

    private val userViewModel: UserViewModel by viewModels()
    private val workoutViewModel: WorkoutViewModel by viewModels()
    private var timeElapsed = 0.0
    private lateinit var serviceIntent: Intent


    private val updateTime: BroadcastReceiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            timeElapsed = intent.getDoubleExtra(WorkoutTimerService.TIME_ELAPSED, 0.0)
            workoutViewModel.timerText = getTimeStringFromDouble(timeElapsed)
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        serviceIntent = Intent(this, WorkoutTimerService::class.java)
        registerReceiver(
            updateTime,
            IntentFilter(WorkoutTimerService.TIMER_UPDATED)
        )
        setContent {

            InFitTheme {

                val workoutViewModel = hiltViewModel<WorkoutViewModel>()
                val userViewModel = hiltViewModel<UserViewModel>()
                navController = rememberNavController()
                RootNavGraph(navController,userViewModel,workoutViewModel)

            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        stopService(serviceIntent)
    }
}


