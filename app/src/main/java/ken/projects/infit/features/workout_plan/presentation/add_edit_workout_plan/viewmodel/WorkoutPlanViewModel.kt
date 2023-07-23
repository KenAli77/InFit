package ken.projects.infit.features.workout_plan.presentation.add_edit_workout_plan.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import ken.projects.infit.R
import ken.projects.infit.features.auth.presentation.register.events.user_input.SignUpUserInputEvent
import ken.projects.infit.features.auth.presentation.register.events.validation.SignUpValidationEvent
import ken.projects.infit.features.workout_plan.presentation.add_edit_workout_plan.events.button_click.WorkoutPlanClickEvent
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
                val days = mutableListOf<DayOfWeek>()
                days.addAll(state.frequency)
                if (event.checked) {
                    days.add(event.day)
                } else {
                    days.remove(event.day)
                }
                state.copy(frequency = days)
            }
            is WorkoutPlanUserInputEvent.EnteredWorkoutGoal -> {
                state.copy(goal = event.goal)
            }
            is WorkoutPlanUserInputEvent.EnteredWorkoutName -> {
                state.copy(name = event.name)
            }
        }
    }

    fun onClickEvent(event: WorkoutPlanClickEvent) {
        viewModelScope.launch {
            when (event) {
                WorkoutPlanClickEvent.BackButtonClickEvent -> {
//                    pagerEvent.send(event)
                }
                WorkoutPlanClickEvent.FinishButtonClickEvent -> {
//                    pagerEvent.send(event)
                }
                WorkoutPlanClickEvent.NextButtonClickEvent -> {
//                    pagerEvent.send(event)
                }
                is WorkoutPlanClickEvent.PagerNavClickEvent -> {
                    if(event.id == R.string.next){
                        pagerEvent.send(WorkoutPlanPagerEvent.NavigateForward)
                    }
                    if(event.id == R.string.finish){

                    }
                    if(event.id == R.string.back){
                        pagerEvent.send(WorkoutPlanPagerEvent.NavigateBack)
                    }

                }
            }
        }
    }

    fun onPageChange(page:Int){
        var text = R.string.next
        var backNavVisible = false
        if(page == 0){
            text = R.string.next
        }
        if(page >0){
            text = R.string.finish
            backNavVisible = true
        }
        state = state.copy(
            pagerNavText = text,
            pagerBackNavVisible = backNavVisible
        )

    }
}