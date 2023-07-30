package ken.projects.infit.features.workout_plan.domain.use_case

import ken.projects.infit.core.domain.GenericResponse
import ken.projects.infit.features.workout_plan.domain.repositories.WorkoutPlanRepository

class DeleteWorkoutPlan(repo: WorkoutPlanRepository) {

    operator fun invoke(): GenericResponse {
        return GenericResponse()
    }
}