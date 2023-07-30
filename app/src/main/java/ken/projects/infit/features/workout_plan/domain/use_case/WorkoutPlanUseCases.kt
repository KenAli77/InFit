package ken.projects.infit.features.workout_plan.domain.use_case

data class WorkoutPlanUseCases(
    val createWorkoutPlan: CreateWorkoutPlan,
    val deleteWorkoutPlan: DeleteWorkoutPlan,
    val editWorkoutPlan: EditWorkoutPlan,
)
