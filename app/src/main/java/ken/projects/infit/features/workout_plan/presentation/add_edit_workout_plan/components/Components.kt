package ken.projects.infit.features.workout_plan.presentation.add_edit_workout_plan.components

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.chargemap.compose.numberpicker.NumberPicker
import ken.projects.infit.R
import ken.projects.infit.core.presentation.components.InputText
import ken.projects.infit.core.presentation.components.Paragraph
import ken.projects.infit.core.utils.customClickable
import ken.projects.infit.features.workout_plan.data.enums.Difficulty
import ken.projects.infit.features.workout_plan.data.enums.Goal
import ken.projects.infit.features.workout_plan.presentation.add_edit_workout_plan.events.user_input.WorkoutPlanUserInputEvent
import ken.projects.infit.features.workout_plan.presentation.add_edit_workout_plan.state.WorkoutPlanState
import ken.projects.infit.features.workout_plan.presentation.add_edit_workout_plan.viewmodel.WorkoutPlanViewModel
import ken.projects.infit.ui.composables.home.SubHeading
import ken.projects.infit.ui.composables.home.Title
import ken.projects.infit.ui.theme.*
import java.time.DayOfWeek

@Composable
fun WorkoutPlanSetUpPager1(
    modifier: Modifier = Modifier,
    state: WorkoutPlanState,
    onUserInputEvent: (WorkoutPlanUserInputEvent)->Unit
) {

    Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(20.dp)) {
        SubHeading(
            text = stringResource(R.string.choose_a_name), modifier = Modifier
                .padding(horizontal = 5.dp)
        )
        InputText(
            value = state.name,
            onValueChange = {
                onUserInputEvent(
                    WorkoutPlanUserInputEvent.EnteredWorkoutName(
                        it
                    )
                )
            },
        )
        Spacer(modifier = Modifier.height(10.dp))
        SubHeading(
            text = stringResource(R.string.select_training_days),
        )

        LazyRow() {
            DayOfWeek.values().forEach {
                item {
                    WeekdaysChipItem(day = it, onCheck = { day, checked ->
                        onUserInputEvent(
                            WorkoutPlanUserInputEvent.EnteredWorkoutFrequency(
                                day = day,
                                checked = checked
                            )
                        )
                    })
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))

        SubHeading(
            text = stringResource(R.string.difficulty_level),
        )
        LazyRow() {
            Difficulty.values().forEach {
                item {
                    DifficultyLevelItems(
                        difficulty = it,
                        onCheck = {
                            onUserInputEvent(
                                WorkoutPlanUserInputEvent.EnteredWorkoutDifficulty(
                                    it
                                )
                            )
                        },
                        checked = state.difficulty == it
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(10.dp))

        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceBetween) {


            SubHeading(
                text = stringResource(R.string.duration),
            )

            Row(
                modifier = Modifier.padding(horizontal = 10.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                NumberPicker(
                    value = state.duration,
                    onValueChange = {
                        onUserInputEvent(
                            WorkoutPlanUserInputEvent.EnteredWorkoutDuration(
                                it
                            )
                        )
                    },
                    range = 10..120,
                    textStyle = TextStyle(color = white),
                    dividersColor = holoGreen,
                    modifier = Modifier.width(100.dp)
                )
                Title(text = stringResource(R.string.minutes))
            }
        }




    }
}

@Composable
fun WorkoutPlanSetUpPager2(modifier: Modifier = Modifier, goal: Goal,onSelected: (Goal) -> Unit) {

    Column(modifier = modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(10.dp)) {

        SubHeading(text = stringResource(id = R.string.select_goal))

        Goal.values().forEach {
            GoalCard(goal = it, onSelected = { onSelected(it) }, selected = goal == it, modifier = Modifier.padding(vertical = 20.dp))
        }

    }

}

@Composable
fun WorkoutPlanSetUpPager3(modifier: Modifier = Modifier, state: WorkoutPlanState) {




}


@Composable
fun GoalCard(modifier: Modifier = Modifier, goal: Goal, onSelected: () -> Unit, selected: Boolean) {
    val border = if(selected){
        BorderStroke(width = 1.dp, color = holoGreen)
    }else{
        BorderStroke(0.dp, Color.Transparent)
    }
    Surface(modifier = modifier
        .height(150.dp)
        .fillMaxWidth()
        .padding(horizontal = 10.dp)
        .customClickable { onSelected() },
        shape = RoundedCornerShape(20.dp),
        color = darkBlue,
        border = border,
    elevation = 10.dp) {
        Column(modifier = Modifier.padding(8.dp), verticalArrangement = Arrangement.spacedBy(10.dp), horizontalAlignment = Alignment.Start) {
          Title(text = stringResource(id =goal.title), fontWeight = FontWeight.SemiBold)
          Paragraph(text = stringResource(id = goal.description))
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
        .customClickable {
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
    difficulty: Difficulty,
    onCheck: (Difficulty) -> Unit,
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
        .customClickable {
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
