package ken.projects.infit.features.workout_plan.domain.use_case

import ken.projects.infit.core.domain.GenericResponse
import ken.projects.infit.features.workout_plan.data.models.WorkoutPlan
import ken.projects.infit.features.workout_plan.domain.repositories.WorkoutPlanRepository

class CreateWorkoutPlan(repo: WorkoutPlanRepository) {

    operator fun invoke(workout:WorkoutPlan):GenericResponse{

        return GenericResponse()
    }

}