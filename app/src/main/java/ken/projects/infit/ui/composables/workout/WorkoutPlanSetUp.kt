package ken.projects.infit.ui.composables.workout

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.chargemap.compose.numberpicker.NumberPicker
import ken.projects.infit.R
import ken.projects.infit.data.models.WorkoutPlan
import ken.projects.infit.ui.composables.RegularButton
import ken.projects.infit.ui.composables.home.Heading
import ken.projects.infit.ui.composables.home.SubHeading
import ken.projects.infit.ui.composables.home.Title
import ken.projects.infit.ui.navigation.Screens
import ken.projects.infit.ui.theme.*
import ken.projects.infit.util.DifficultyLevels
import ken.projects.infit.util.DifficultyLevels.Companion.Advanced
import ken.projects.infit.util.DifficultyLevels.Companion.Beginner
import ken.projects.infit.util.DifficultyLevels.Companion.Intermediate
import ken.projects.infit.viewmodel.WorkoutViewModel
import java.time.DayOfWeek

@Composable
fun WorkoutPlanSetUpScreen(workoutViewModel: WorkoutViewModel, navController: NavHostController) =

    with(workoutViewModel) {

        var workoutPlanName by remember { mutableStateOf("") }

        Surface(modifier = Modifier.fillMaxSize(), color = darkBlue) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 5.dp),
                verticalArrangement = Arrangement.SpaceAround,
                horizontalAlignment = Alignment.Start
            ) {

                Heading(
                    text = stringResource(R.string.set_up_workout_plan_heading),
                    modifier = Modifier.padding(start = 5.dp, top = 10.dp)
                )

                SubHeading(
                    text = stringResource(R.string.choose_a_name), modifier = Modifier
                        .padding(horizontal = 5.dp)
                )
                TextField(
                    value = workoutPlanName,
                    onValueChange = { workoutPlanName = it },
                    modifier = Modifier.padding(horizontal = 5.dp),
                    textStyle = TextStyle(fontSize = 18.sp)
                )

                SubHeading(
                    text = stringResource(R.string.select_training_days),
                    modifier = Modifier.padding(horizontal = 5.dp)
                )

                LazyRow() {
                    DayOfWeek.values().forEach {

                        item {

                            WeekdaysChipItem(day = it, onCheck = { day, checked ->

                                if (checked) addDay(day) else removeDay(
                                    day
                                )


                            })
                        }

                    }
                }

                SubHeading(
                    text = stringResource(R.string.difficulty_level),
                    modifier = Modifier.padding(horizontal = 5.dp)
                )

                LazyRow() {
                    val levels = listOf(Beginner, Intermediate, Advanced)

                    levels.forEach {

                        item {

                            DifficultyLevelItems(
                                difficulty = it,
                                onCheck = { selectDifficulty(it) },
                                checked = selectedDifficulty == it
                            )
                        }

                    }
                }

                SubHeading(text = stringResource(R.string.duration), modifier = Modifier.padding(horizontal = 5.dp))

                var duration by remember { mutableStateOf(0) }

                Row(
                    modifier = Modifier.padding(horizontal = 10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    NumberPicker(
                        value = duration,
                        onValueChange = { duration = it },
                        range = 10..120,
                        textStyle = TextStyle(color = white),
                        dividersColor = holoGreen,
                        modifier = Modifier.width(100.dp)
                    )
                    Title(text = stringResource(R.string.minutes))
                }

                RegularButton(
                    text = stringResource(R.string.save),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ) {
                    if (workoutPlanName.isNotEmpty() && selectedDays.isNotEmpty()) {
                        val workoutPlan = WorkoutPlan(
                            name = workoutPlanName,
                            workouts = selectedDays.toList() as ArrayList<DayOfWeek>,
                            difficulty = selectedDifficulty,
                            duration = duration
                        )
                        addWorkoutPlan(workoutPlan)
                        navController.navigate(Screens.Home.route)
                    }
                }
            }


        }


    }

@Composable
fun WeekdaysChipItem(
    modifier: Modifier = Modifier,
    onCheck: (DayOfWeek, Boolean) -> Unit,
    day: DayOfWeek
) {

    val text = day.name.substring(0..1).uppercase()


    var checked by remember {
        mutableStateOf(false)
    }
    var backgroundColor by remember { mutableStateOf(lightBlue) }

    backgroundColor = if (checked) holoGreen else veryDarkBlue.copy(0.6f)

    var textColor by remember {
        mutableStateOf(Color.Black)
    }


    textColor = if (checked) Color.Black else white

    Surface(color = backgroundColor, shape = CircleShape, modifier = modifier
        .clickable {
            checked = !checked
            onCheck(day, checked)
        }
        .size(50.dp)
        .padding(5.dp),
        elevation = 10.dp) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceAround,
            modifier = Modifier.fillMaxSize()
        ) {

            Text(text = text, color = textColor)

        }


    }
}


@Composable
fun DifficultyLevelItems(
    difficulty: DifficultyLevels.Difficulty,
    onCheck: (DifficultyLevels.Difficulty) -> Unit,
    checked: Boolean
) {

    var backgroundColor by remember { mutableStateOf(holoGreen) }

    backgroundColor =
        if (checked) holoGreen else veryDarkBlue

    var textColor by remember {
        mutableStateOf(Color.Black)
    }

    textColor = if (checked) Color.Black else white

    Surface(color = backgroundColor, shape = RoundedCornerShape(40.dp), modifier = Modifier
        .clickable {
            onCheck(difficulty)
        }
        .padding(5.dp),
        elevation = 10.dp) {

        Row(
            modifier = Modifier.padding(10.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(text = stringResource(id = difficulty.difficulty!!), color = textColor)

        }

    }

}



