package ken.projects.infit.features.workout_plan.presentation.add_edit_workout_plan.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ken.projects.infit.R
import ken.projects.infit.features.workout_plan.presentation.add_edit_workout_plan.events.button_click.WorkoutPlanClickEvent
import ken.projects.infit.features.workout_plan.presentation.add_edit_workout_plan.events.dialog.WorkoutPlanDialogEvent
import ken.projects.infit.features.workout_plan.presentation.add_edit_workout_plan.events.pager.WorkoutPlanPagerEvent
import ken.projects.infit.features.workout_plan.presentation.add_edit_workout_plan.events.user_input.WorkoutPlanUserInputEvent
import ken.projects.infit.features.workout_plan.presentation.add_edit_workout_plan.events.validation.WorkoutPlanValidationEvent
import ken.projects.infit.features.workout_plan.presentation.add_edit_workout_plan.state.WorkoutPlanState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.DayOfWeek
import javax.inject.Inject

@HiltViewModel
class WorkoutPlanViewModel @Inject constructor() : ViewModel() {

    var state by mutableStateOf(WorkoutPlanState())
        private set


    private val validationEventChannel = Channel<WorkoutPlanValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

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
                state.copy(setsTotal = event.sets)
            }
        }
    }

    fun onClickEvent(event: WorkoutPlanClickEvent) {
        viewModelScope.launch {
            when (event) {
                WorkoutPlanClickEvent.AddExerciseButtonClickEvent -> {}
                WorkoutPlanClickEvent.FinishButtonClickEvent -> {}
                WorkoutPlanClickEvent.SaveExerciseClickEvent -> {}
            }
        }
    }

    fun onDialogEvent(event: WorkoutPlanDialogEvent) {
        state = when (event) {
            is WorkoutPlanDialogEvent.ExerciseDialogEvent -> {
                state.copy(openExerciseDialog = event.visible)
            }
            is WorkoutPlanDialogEvent.EquipmentMenuEvent -> {
                state.copy(equipmentMenuExpanded = event.visible)
            }
            is WorkoutPlanDialogEvent.ExerciseMenuEvent -> {
                state.copy(exerciseMenuExpanded = event.visible)
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
                viewModelScope.launch {
                    onPageChange(event.currentPage + 1)
                    pagerEvent.send(event)
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
        if (page >= 2) {
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
}