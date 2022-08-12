package ken.projects.infit.data.models.states

import ken.projects.infit.data.models.WorkoutPlan

data class WorkoutPlanState(
    val workoutPlan:WorkoutPlan? = null,
    val loading:Boolean = false,
    val error:String? = null
)
