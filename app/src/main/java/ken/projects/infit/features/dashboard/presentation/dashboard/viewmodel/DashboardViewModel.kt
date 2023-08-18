package ken.projects.infit.features.dashboard.presentation.dashboard.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ken.projects.infit.features.dashboard.domain.use_case.DashboardUseCases
import kotlinx.coroutines.launch
import javax.inject.Inject

class DashboardViewModel
@Inject constructor (private val useCases: DashboardUseCases) : ViewModel() {

    init {
        getWorkoutPlan()
    }

    private fun getWorkoutPlan(){
        viewModelScope.launch {
            val result = useCases.getWorkouts.invoke()

            if(result.success){

            } else {

            }
        }
    }

}