package ken.projects.infit.ui.composables.exercises

import android.content.Intent
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import ken.projects.infit.R
import ken.projects.infit.data.models.*
import ken.projects.infit.ui.composables.FloatingAddButton
import ken.projects.infit.ui.composables.RegularButton
import ken.projects.infit.ui.composables.home.Heading
import ken.projects.infit.ui.composables.home.SubHeading
import ken.projects.infit.ui.theme.darkBlue
import ken.projects.infit.ui.theme.holoGreen
import ken.projects.infit.ui.theme.lightBlue
import ken.projects.infit.ui.theme.white
import ken.projects.infit.viewmodel.WorkoutViewModel

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun ExerciseDetailScreen(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    workoutViewModel: WorkoutViewModel
) = with(workoutViewModel) {

    getExercises()

    val heading = selectedMuscleGroup

    val exercises = filteredExerciseList

    var openDialog by remember { mutableStateOf(false) }

    var selectedExercise by remember { mutableStateOf("") }

    LaunchedEffect(key1 = openDialog, block = {getExercises()})

    val equipments = equipments().toSet().toList()
    var selectedEquipment by remember { mutableStateOf(equipments[0]) }
    var eqBoxExpanded by remember { mutableStateOf(false) }


    if (openDialog) Dialog(onDismissRequest = { openDialog = false })
    {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            color = lightBlue,
            shape = RoundedCornerShape(40.dp)
        ) {

            Column(
                verticalArrangement = Arrangement.spacedBy(20.dp),
                horizontalAlignment = Alignment.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)

            ) {
                SubHeading(text = stringResource(R.string.add_new_exercise))

                TextField(
                    readOnly = false,
                    value = selectedExercise,
                    onValueChange = { selectedExercise = it },
                    label = { Text(stringResource(R.string.exercise).lowercase(), color = holoGreen) },
                    textStyle = TextStyle(fontSize = 20.sp)
                )

                ExposedDropdownMenuBox(
                    expanded = eqBoxExpanded,
                    onExpandedChange = {
                        eqBoxExpanded = !eqBoxExpanded
                    }
                ) {
                    TextField(
                        readOnly = true,
                        value = stringResource(selectedEquipment.name!!),
                        onValueChange = { },
                        label = { Text(stringResource(R.string.equipment), color = holoGreen) },
                        trailingIcon = {
                            ExposedDropdownMenuDefaults.TrailingIcon(
                                expanded = eqBoxExpanded,

                                )
                        },
                        colors = ExposedDropdownMenuDefaults.textFieldColors(
                            trailingIconColor = holoGreen,
                            focusedTrailingIconColor = holoGreen,
                            disabledTrailingIconColor = holoGreen
                        ),
                        textStyle = TextStyle(fontSize = 20.sp)
                    )
                    ExposedDropdownMenu(
                        expanded = eqBoxExpanded,
                        onDismissRequest = {
                            eqBoxExpanded = false
                        }
                    ) {
                        equipments.forEach { selectionOption ->
                            DropdownMenuItem(
                                onClick = {
                                    selectedEquipment = selectionOption
                                    eqBoxExpanded = false
                                }
                            ) {
                                Text(text = stringResource(selectionOption.name!!))
                            }
                        }
                    }
                }

                val context = LocalContext.current

                RegularButton(
                    text = stringResource(R.string.add),
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    onClick = {
                        getExercises()

                        if (selectedExercise.isNotEmpty()) {
                            openDialog = false
                            addNewExercise(
                                Exercise(
                                    name = selectedExercise.replaceFirstChar { it.uppercase() },
                                    equipments = selectedEquipment.equipments,
                                    targetMuscles = muscleGroup.toList()
                                )
                            )
                            getExercises()
                        } else {
                            Toast.makeText(
                                context,
                                context.getString(R.string.please_enter_exercise_name),
                                Toast.LENGTH_SHORT
                            ).show()
                        }


                    }
                )
            }

        }
    }


    Surface(color = darkBlue, modifier = Modifier.fillMaxSize()) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 20.dp)
                .padding(horizontal = 15.dp),
            verticalArrangement = Arrangement.spacedBy(40.dp)
        ) {

            Heading(text = heading)

            LazyColumn(modifier = Modifier.height(500.dp)) {

                items(exercises) { exercise ->
                    val context = LocalContext.current
                    ExerciseItem(exercise = exercise, modifier = Modifier.clickable {
                        val intent = Intent(Intent.ACTION_SEARCH)
                        intent.setPackage("com.google.android.youtube")
                        intent.putExtra("query", "${exercise.name} tutorial gym")
                        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        context.startActivity(intent)
                    })
                    Spacer(modifier = Modifier.height(20.dp))

                }

            }

            FloatingAddButton(
                onClick = { openDialog = true },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

        }

    }

}

@Composable
fun ExerciseItem(exercise: Exercise, modifier: Modifier = Modifier) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        SubHeading(text = exercise.name.toString(), color = white)
        Icon(
            imageVector = Icons.Rounded.KeyboardArrowRight,
            contentDescription = null,
            tint = holoGreen,
            modifier = Modifier.size(50.dp)
        )


    }
}




