package ken.projects.infit.features.workout_plan.presentation.add_edit_workout_plan.viewmodel

import ken.projects.infit.features.workout_plan.data.models.ExerciseItem
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ken.projects.infit.R
import ken.projects.infit.core.navigation.Screens
import ken.projects.infit.features.workout_plan.domain.use_case.workout_plan.WorkoutPlanUseCases
import ken.projects.infit.features.workout_plan.presentation.add_edit_workout_plan.events.button_click.WorkoutPlanClickEvent
import ken.projects.infit.features.workout_plan.presentation.add_edit_workout_plan.events.dialog.WorkoutPlanDialogEvent
import ken.projects.infit.features.workout_plan.presentation.add_edit_workout_plan.events.pager.WorkoutPlanPagerEvent
import ken.projects.infit.features.workout_plan.presentation.add_edit_workout_plan.events.user_input.WorkoutPlanUserInputEvent
import ken.projects.infit.features.workout_plan.presentation.add_edit_workout_plan.events.navigation.WorkoutPlanNavigationEvent
import ken.projects.infit.features.workout_plan.presentation.add_edit_workout_plan.state.WorkoutPlanState
import ken.projects.infit.features.workout_plan.presentation.add_edit_workout_plan.utils.toWorkoutPlan
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import javax.inject.Inject

@HiltViewModel
class WorkoutPlanViewModel @Inject constructor(val useCases:WorkoutPlanUseCases) : ViewModel() {

    var state by mutableStateOf(WorkoutPlanState())
        private set


    private val navigationEventChannel = Channel<WorkoutPlanNavigationEvent>()
    val navigationEvent = navigationEventChannel.receiveAsFlow()

    private val pagerEvent = Channel<WorkoutPlanPagerEvent>()
    val pagerEvents = pagerEvent.receiveAsFlow()


    fun onUserInputEvent(event: WorkoutPlanUserInputEvent) {

        state = when (event) {
            is WorkoutPlanUserInputEvent.EnteredWorkoutDifficulty -> {
                state.copy(difficulty = event.difficulty)
            }
            is WorkoutPlanUserInputEvent.EnteredWorkoutDuration -> {
                state.copy(duration = event.duration)
            }
            is WorkoutPlanUserInputEvent.EnteredWorkoutFrequency -> {
                val days = changeDaysSelection(event)
                state.copy(frequency = days)
            }
            is WorkoutPlanUserInputEvent.EnteredWorkoutGoal -> {
                state.copy(goal = event.goal)
            }
            is WorkoutPlanUserInputEvent.EnteredWorkoutName -> {
                state.copy(name = event.name)
            }
            is WorkoutPlanUserInputEvent.SelectedEquipment -> {
                state.copy(equipment = event.equipment)
            }
            is WorkoutPlanUserInputEvent.SelectedExercise -> {
                state.copy(exercise = event.exercise)
            }
            is WorkoutPlanUserInputEvent.EnteredSetTotal -> {
                Log.e("sets total","${event.sets}")
                state.copy(setsTotal = event.sets)
            }
        }
    }

    fun onClickEvent(event: WorkoutPlanClickEvent) {
        viewModelScope.launch {
            when (event) {
                WorkoutPlanClickEvent.AddExerciseButtonClickEvent -> {}
                WorkoutPlanClickEvent.FinishButtonClickEvent -> {}
                WorkoutPlanClickEvent.SaveExerciseClickEvent -> {
                    val exerciseItem = ExerciseItem(
                        exercise = state.exercise,
                        equipment = state.equipment,
                        sets = state.setsTotal
                    )
                    val exercises = state.exerciseItems.toMutableList()
                    exercises.add(exerciseItem)
                    state = state.copy(exerciseItems = exercises )
                }
                is WorkoutPlanClickEvent.RemoveExerciseClickEvent -> {
                    Log.e("exercise","index: ${event.index}")
                    Log.e("exercise","size: ${state.exerciseItems.size}")
                    val exercises = state.exerciseItems.toMutableList()
                    if(event.index >= 0 && event.index < exercises.size)
                    exercises.removeAt(event.index)
                    state = state.copy(exerciseItems = exercises)
                }
            }
        }
    }

    fun onDialogEvent(event: WorkoutPlanDialogEvent) {
        state = when (event) {
            is WorkoutPlanDialogEvent.ExerciseDialogEvent -> {
                state.copy(openExerciseDialog = event.visible)
            }
            is WorkoutPlanDialogEvent.EquipmentMenuEvent -> {
                Log.e("equipment","is visible is ${event.visible}")
                state.copy(equipmentMenuExpanded = !state.equipmentMenuExpanded)
            }
            is WorkoutPlanDialogEvent.ExerciseMenuEvent -> {
                state.copy(exerciseMenuExpanded = !state.exerciseMenuExpanded)
            }
        }
    }

    fun onPagerEvent(event: WorkoutPlanPagerEvent) {
        when (event) {
            is WorkoutPlanPagerEvent.NavigateBack -> {
                viewModelScope.launch {
                    onPageChange(event.currentPage - 1)
                    pagerEvent.send(event)
                }
            }
            is WorkoutPlanPagerEvent.NavigateForward -> {
                Log.e("pagerEvent","page: ${event.currentPage}")
                Log.e("pagerEvent","pageCount: ${state.pagerPageCount -1}")

                if(event.currentPage != state.pagerPageCount -1){
                    viewModelScope.launch {
                        onPageChange(event.currentPage + 1)
                        pagerEvent.send(event)
                    }
                }
                if (event.currentPage == state.pagerPageCount -1) {
                    Log.e("pagerEvent","submitting worktouplan")
                    submitWorkoutPlan()
                }

            }
        }
    }

    fun onPageChange(page: Int) {
        if (page > state.pagerPageCount || page < 0) {
            return
        }
        var text = R.string.next
        var backNavVisible = false
        if (page >= 0) {
            text = R.string.next
        }
        if (page > 0) {
            backNavVisible = true
        }
        if (page >= 1) {
            text = R.string.finish
        }
        state = state.copy(
            pagerNavText = text,
            pagerBackNavVisible = backNavVisible
        )
    }

    private fun changeDaysSelection(event: WorkoutPlanUserInputEvent.EnteredWorkoutFrequency): List<DayOfWeek> {
        val days = mutableListOf<DayOfWeek>()
        days.addAll(state.frequency)
        if (event.checked) {
            days.add(event.day)
        } else {
            days.remove(event.day)
        }

        return days
    }

    private fun submitWorkoutPlan(){

        viewModelScope.launch {
            val workoutPLan = state.toWorkoutPlan()
            val result = useCases.createWorkoutPlan.invoke(workoutPLan)
            if(!result.success){
               state = state.copy(error = result.errorMessage)
            } else {
                navigationEventChannel.send(WorkoutPlanNavigationEvent.Navigate(Screens.Workout.route))
                Log.e("submitWorkoutPlan","success!")
            }
        }

    }

}