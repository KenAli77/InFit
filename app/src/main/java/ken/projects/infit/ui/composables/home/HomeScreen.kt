package ken.projects.infit.ui.composables.home

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.List
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ken.projects.infit.R
import ken.projects.infit.ui.composables.*
import ken.projects.infit.ui.navigation.Screens
import ken.projects.infit.ui.theme.*
import ken.projects.infit.viewmodel.UserViewModel
import ken.projects.infit.viewmodel.WorkoutViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDateTime

@OptIn(ExperimentalComposeUiApi::class)
@SuppressLint("CoroutineCreationDuringComposition")
@Composable
fun HomeScreen(
    navController: NavHostController = rememberNavController(),
    userViewModel: UserViewModel,
    workoutViewModel: WorkoutViewModel = viewModel(),
    scaffoldState: ScaffoldState
) = with(workoutViewModel) {


    getUser(userViewModel.signInState.uid.toString())
    getWorkoutPlan()
    getExercises()

    val scope = rememberCoroutineScope()
    val state = workoutPlanState
    val date = workoutDay
    val selectedDay = calendarSelection
    var delay by remember { mutableStateOf(true) }
    var openDialog by remember { mutableStateOf(false) }
    var openLogoutDialog by remember { mutableStateOf(false) }


    BackHandler(true) {

        openLogoutDialog = true
    }

    LaunchedEffect(key1 = openDialog){
        getWorkoutPlan()
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(id = R.drawable.home_screen_background),
                contentScale = ContentScale.Crop
            ),
        color = Color.Transparent
    ) {

        if (openDialog) {

            Dialog(
                onDismissRequest = { openDialog = false },
                properties = DialogProperties(usePlatformDefaultWidth = false)

            ) {
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .padding(vertical = 8.dp, horizontal = 15.dp),
                    color = darkBlue,
                    elevation = 20.dp,

                    ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .padding(vertical = 10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceAround
                    ) {

                        SubHeading(text = stringResource(R.string.delete_workout_plan), color = holoGreen)

                        Title(
                            text = stringResource(R.string.data_deletion_alert_text),

                            )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(
                                20.dp,
                                Alignment.CenterHorizontally
                            )
                        ) {

                            RegularButton(text = stringResource(R.string.confirm), onClick = {
                                deleteWorkoutPlan()
                              //  getWorkoutPlan()
                                openDialog = false
                            })

                            RegularButton(text = stringResource(R.string.dismiss), onClick = {
                                openDialog = false
                            })

                        }


                    }

                }

            }

        }
        if (openLogoutDialog) {

            Dialog(
                onDismissRequest = { openLogoutDialog = false },
                properties = DialogProperties(usePlatformDefaultWidth = false)

            ) {
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    modifier = Modifier
                        .padding(vertical = 8.dp, horizontal = 15.dp),
                    color = darkBlue,
                    elevation = 20.dp,

                    ) {

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .padding(vertical = 10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.SpaceAround
                    ) {

                        SubHeading(text = stringResource(R.string.logout), color = holoGreen)

                        Title(
                            text = stringResource(R.string.logout_alert_text),

                            )

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.spacedBy(
                                20.dp,
                                Alignment.CenterHorizontally
                            )
                        ) {

                            RegularButton(text = stringResource(R.string.confirm), onClick = {
                                userViewModel.logOut()
                                openLogoutDialog = false
                                navController.navigateUp()

                            })

                            RegularButton(text = stringResource(R.string.dismiss), onClick = {
                                openLogoutDialog = false
                            })

                        }


                    }

                }

            }

        }

        val snackbarText = stringResource(R.string.close)
        state.error?.let {

            if (workoutPlanState.workoutPlan == null) {
                scope.launch {
                    scaffoldState.snackbarHostState.showSnackbar(
                        it,
                        snackbarText,
                        SnackbarDuration.Long
                    )
                }
            }
        }


        userViewModel.signInState.uid?.let {

            state.workoutPlan?.let { workoutPlan ->

                var isWorkoutDay: Boolean by remember { mutableStateOf(false) }
                var isTodayWorkoutDay: Boolean by remember { mutableStateOf(false) }

                workoutPlan.workouts?.let { workouts ->

                    isTodayWorkoutDay =
                        if (selectedDay.dayOfYear == LocalDateTime.now().dayOfYear) {
                            workouts.contains(selectedDay.dayOfWeek)
                        } else false

                    isWorkoutDay =
                        workouts.contains(calendarSelection.dayOfWeek)

                }

                Column(
                    verticalArrangement = Arrangement.SpaceAround,
                    modifier = Modifier
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.Start
                ) {

                    Heading(
                        text = stringResource(R.string.hi)+" "+ user?.userName?.replaceFirstChar { it.uppercase() },
                        modifier = Modifier.padding(start = 15.dp),
                    )
                    SubHeading(
                        text = stringResource(R.string.your_schedule),
                        modifier = Modifier.padding(start = 15.dp)
                    )
                    CalendarDisplay(
                        modifier = Modifier,
                        workoutPlan.workouts,
                        workoutViewModel = workoutViewModel
                    )
                    Heading(
                        text = date,
                        modifier = Modifier.padding(start = 15.dp)
                    )

                    if (isWorkoutDay) {
                        TrainingCard(
                            modifier = Modifier.padding(horizontal = 15.dp),
                            workoutPlan,
                            onClick = { navController.navigate(route = Screens.WorkoutDetails.route) }
                        )

                        CommandsDisplay(
                            modifier = Modifier.padding(horizontal = 15.dp),
                            iconStart = Icons.Rounded.List,
                            iconCenter = Icons.Rounded.PlayArrow,
                            iconEnd = Icons.Rounded.Delete,
                            iconStartClick = {
                                navController.navigate(route = Screens.WorkoutDetails.route)
                            },
                            iconCenterClick = { navController.navigate(Screens.Workout.route) },
                            iconEndClick = {
                                openDialog = true
                            },
                            isWorkoutDay = isTodayWorkoutDay
                        )
                    } else {
                        RestDayView(
                            modifier = Modifier
                                .padding(horizontal = 15.dp)
                                .height(370.dp)
                        )
                    }

                }

            } ?: kotlin.run {
                EmptyWorkoutPlanView(
                    modifier = Modifier.padding(horizontal = 15.dp),
                    user = user?.userName.toString(),
                    onClick = { navController.navigate(Screens.WorkoutPlanSetUp.route) })
            }
        }
    }

}

@Composable
fun Heading(text: String, modifier: Modifier = Modifier, color: Color = white) {
    Text(
        text = text,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
        fontFamily = outfit,
        modifier = modifier.paddingFromBaseline(top = 16.dp),
        color = color
    )
}

@Composable
fun SubHeading(text: String, modifier: Modifier = Modifier, color: Color = white) {
    Text(
        text = text,
        fontWeight = FontWeight.Normal,
        fontSize = 30.sp,
        fontFamily = outfit,
        modifier = modifier.paddingFromBaseline(top = 16.dp),
        color = color
    )
}

@Composable
fun Title(text: String, modifier: Modifier = Modifier, color: Color = white) {
    Text(
        text = text,
        fontWeight = FontWeight.Light,
        fontFamily = outfit,
        fontSize = 25.sp,
        color = color,
        modifier = modifier
    )
}




