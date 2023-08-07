package ken.projects.infit.features.workout_plan.domain.use_case.workout_plan

import ken.projects.infit.features.workout_plan.domain.use_case.workout_plan.CreateWorkoutPlan
import ken.projects.infit.features.workout_plan.domain.use_case.workout_plan.DeleteWorkoutPlan
import ken.projects.infit.features.workout_plan.domain.use_case.workout_plan.EditWorkoutPlan

data class WorkoutPlanUseCases(
    val createWorkoutPlan: CreateWorkoutPlan,
    val deleteWorkoutPlan: DeleteWorkoutPlan,
    val editWorkoutPlan: EditWorkoutPlan,
)
