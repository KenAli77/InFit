package ken.projects.infit.features.workout_plan.domain.use_case.validation

import ken.projects.infit.features.workout_plan.data.models.WorkoutPlan
import ken.projects.infit.features.workout_plan.presentation.add_edit_workout_plan.state.WorkoutPlanState

class ValidateWorkoutPlan {
    operator fun invoke(state: WorkoutPlanState){

        state.equipment?.let {  }


    }
}