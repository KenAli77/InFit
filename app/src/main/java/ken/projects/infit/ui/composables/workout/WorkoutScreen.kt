package ken.projects.infit.ui.composables.workout

import android.content.Intent
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ken.projects.infit.R
import ken.projects.infit.data.service.WorkoutTimerService
import ken.projects.infit.data.service.WorkoutTimerService.Companion.TIMER_RUNNING
import ken.projects.infit.ui.composables.CommandsDisplay
import ken.projects.infit.ui.composables.RegularButton
import ken.projects.infit.ui.composables.home.SubHeading
import ken.projects.infit.ui.composables.home.Title
import ken.projects.infit.ui.navigation.Screens
import ken.projects.infit.ui.theme.darkBlue
import ken.projects.infit.ui.theme.white
import ken.projects.infit.viewmodel.WorkoutViewModel

@Composable
fun WorkoutScreen(
    workoutViewModel: WorkoutViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) = with(workoutViewModel) {

    val context = LocalContext.current
    var openDialog by remember { mutableStateOf(false) }
    var exerciseProgress by remember { mutableStateOf("") }


    var reps by remember { mutableStateOf(selectedSetItem.reps.toString()) }
    var weight by remember { mutableStateOf(selectedSetItem.weight.toString()) }

    if (currentSetItemIndex == 0) {
        getFirstExercise()
    }

    if (!openDialog) {
        reps = selectedSetItem.reps.toString()
        weight = selectedSetItem.weight.toString()
    }
    if (openDialog) {


        Dialog(onDismissRequest = { openDialog = false })
        {
            Surface(
                shape = RoundedCornerShape(20.dp),
                modifier = Modifier
                    .width(800.dp)
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

                    SubHeading(text = stringResource(R.string.edit_set))

                    Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Title(text = stringResource(id = R.string.kg).lowercase() )
                        TextField(
                            value = weight,
                            onValueChange = { weight = it },
                            modifier = Modifier.width(100.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                            colors = TextFieldDefaults.textFieldColors()
                        )
                    }

                    Spacer(modifier = Modifier.width(15.dp))
                    Row(
                        horizontalArrangement = Arrangement.SpaceAround,
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Title(stringResource(id = R.string.reps).lowercase())

                        TextField(
                            value = reps,
                            onValueChange = { reps = it },
                            modifier = Modifier.width(100.dp),
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
                        )
                    }

                    Spacer(modifier = Modifier.width(15.dp))

                    RegularButton(
                        text = stringResource(R.string.save),
                        onClick = {
                            if (weight.isNotEmpty() || reps.isNotEmpty()) {
                                editSetItem(weight = weight.toDouble(), reps = reps.toInt())
                                openDialog = false
                            } else {
                                Toast
                                    .makeText(
                                        context,
                                        context.getString(R.string.enter_valid_data),
                                        Toast.LENGTH_SHORT
                                    )
                                    .show()
                            }
                        }
                    )

                }

            }


        }
    }

    getWorkouts()

    Surface(
        modifier = Modifier
            .fillMaxSize(),
        color = darkBlue,
    ) {

        if (isWorkoutStarted) {

            BackHandler(true) {
                Toast.makeText(context, context.getString(R.string.workout_ongoing), Toast.LENGTH_SHORT).show()
            }
        }

        if(workoutState.exerciseItems?.size!! > 0)
        exerciseProgress = "${currentSetItemIndex + 1}/${workoutState.exerciseItems?.size}"

        Column(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(top = 10.dp), horizontalAlignment = CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {

            ProgressItem(
                modifier = Modifier.padding(start = 30.dp),
                exerciseProgress = exerciseProgress,
                timer = timerText
            )

            currentExercise?.let { it ->
                WorkoutCard(
                    exercise = it,
                    workoutViewModel = workoutViewModel,
                    onRemoveSet = { index ->
                        if (isWorkoutStarted) removeSetItem(index) else Toast.makeText(
                            context,
                            context.getString(R.string.start_workout_to_edit),
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    onAddSet = {
                        if (isWorkoutStarted) addSetItem() else Toast.makeText(
                            context,
                            context.getString(R.string.start_workout_to_edit),
                            Toast.LENGTH_SHORT
                        ).show()
                    },
                    onItemClick = {
                        if (isWorkoutStarted)
                            openDialog = !openDialog
                    },
                )
            }
                ?: EmptyWorkoutView(onClick = { navController.navigate(Screens.WorkoutDetails.route) })

            CommandsDisplay(
                modifier = Modifier,
                iconStart = Icons.Rounded.ArrowBack,
                iconCenter = if (!isWorkoutStarted) Icons.Rounded.PlayArrow else Icons.Rounded.Stop,
                iconEnd = Icons.Rounded.ArrowForward,
                iconStartClick = {
                    getPreviousExercise()
                    Log.e("currentExercise", currentExercise?.name.toString())
                },
                iconCenterClick = {
                    if (isWorkoutStarted) {
                        val serviceIntent = Intent(context, WorkoutTimerService::class.java)
                        context.stopService(serviceIntent)
                        serviceIntent.putExtra(TIMER_RUNNING, false)
                        timerText = "00:00:00"
                        updateWorkout()
                        stopWorkout()
                        getWorkouts()
                        Toast.makeText(context, "workout completed!", Toast.LENGTH_SHORT).show()
                    } else {
                        val serviceIntent = Intent(context, WorkoutTimerService::class.java)
                        startWorkout()
                        serviceIntent.putExtra(WorkoutTimerService.TIME_ELAPSED, timeElapsed)
                        serviceIntent.putExtra(TIMER_RUNNING, true)
                        context.startService(serviceIntent)

                    }
                },
                iconEndClick = {
                    getNextExercise()
                    Log.e("currentExercise", currentExercise?.name.toString())
                }
            )

        }

    }
}

@Composable
fun ProgressItem(modifier: Modifier = Modifier, exerciseProgress: String = "1/4", timer: String) {

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp, Alignment.Start),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_timer),
            contentDescription = null,
            tint = white,
            modifier = Modifier.size(30.dp)
        )

        Text(text = timer, color = white, fontSize = 20.sp, fontWeight = FontWeight.SemiBold)

        Spacer(modifier = Modifier.width(50.dp))
        Text(
            text = exerciseProgress,
            color = white,
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
        )

    }
}

@Composable
fun EmptyWorkoutView(onClick: () -> Unit) {

    Surface(
        modifier = Modifier
            .height(520.dp)
            .fillMaxWidth(),
        color = Color.Transparent
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround
        ) {

            SubHeading(text = "you haven't added exercises")
            RegularButton(text = "add exercises", onClick = onClick)

        }
    }
}
