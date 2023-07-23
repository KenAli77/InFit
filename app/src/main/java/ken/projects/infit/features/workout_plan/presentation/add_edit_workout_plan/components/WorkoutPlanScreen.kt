package ken.projects.infit.features.workout_plan.presentation.add_edit_workout_plan.components


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import ken.projects.infit.R
import ken.projects.infit.core.utils.customClickable
import ken.projects.infit.ui.composables.home.Heading
import ken.projects.infit.features.workout_plan.data.enums.Difficulty
import ken.projects.infit.features.workout_plan.presentation.add_edit_workout_plan.events.button_click.WorkoutPlanClickEvent
import ken.projects.infit.features.workout_plan.presentation.add_edit_workout_plan.events.pager.WorkoutPlanPagerEvent
import ken.projects.infit.features.workout_plan.presentation.add_edit_workout_plan.events.user_input.WorkoutPlanUserInputEvent
import ken.projects.infit.features.workout_plan.presentation.add_edit_workout_plan.viewmodel.WorkoutPlanViewModel
import ken.projects.infit.ui.composables.home.Title
import ken.projects.infit.ui.theme.*
import java.time.DayOfWeek

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WorkoutPlanScreen(viewmodel: WorkoutPlanViewModel, navController: NavHostController) =
    with(viewmodel) {
        val pagerState = rememberPagerState(pageCount = {2})

        val context = LocalContext.current

        LaunchedEffect(key1 = context) {
            pagerEvents.collect { event ->
                when (event) {
                    WorkoutPlanPagerEvent.NavigateBack -> pagerState.animateScrollToPage(
                        pagerState.currentPage - 1
                    )
                    WorkoutPlanPagerEvent.NavigateForward -> pagerState.animateScrollToPage(
                        pagerState.currentPage + 1
                    )
                }
            }
        }
        LaunchedEffect(key1 = pagerState.currentPage) {
            onPageChange(pagerState.currentPage)
        }
        Surface(modifier = Modifier.fillMaxSize(), color = darkBlue) {

            ConstraintLayout(modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)) {
                val (header, pager, nav) = createRefs()

                Heading(
                    text = stringResource(R.string.set_up_workout_plan_heading),
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .constrainAs(header) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                        }
                )
                HorizontalPager(
                    userScrollEnabled = false,
                    state = pagerState,
                    modifier = Modifier
                        .constrainAs(pager) {
                            top.linkTo(header.bottom, 20.dp)
                        }
                        .padding(vertical = 10.dp, horizontal = 5.dp)
                ) { index ->
                    if (index == 0) {
                        WorkoutPlanSetUpPager1(
                            state = state,
                            onUserInputEvent = { onUserInputEvent(it) })
                    }
                    if (index == 1) {
                        WorkoutPlanSetUpPager2(
                            goal = state.goal,
                            onSelected = {
                                onUserInputEvent(
                                    WorkoutPlanUserInputEvent.EnteredWorkoutGoal(it)
                                )
                            })
                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .constrainAs(nav) {
                            bottom.linkTo(parent.bottom)
                        },
                ) {
                    if (state.pagerBackNavVisible) {
                        Title(text = stringResource(R.string.back).lowercase(), modifier = Modifier
                            .customClickable {
                                onClickEvent(WorkoutPlanClickEvent.PagerNavClickEvent(R.string.back))
                            }
                            .align(Alignment.BottomStart),
                        fontWeight = FontWeight.Bold,
                        color = holoGreen)
                    }
                    Title(text = stringResource(state.pagerNavText).lowercase(), modifier = Modifier
                        .customClickable {
                            onClickEvent(WorkoutPlanClickEvent.PagerNavClickEvent(state.pagerNavText))
                        }
                        .align(Alignment.BottomEnd), fontWeight = FontWeight.Bold, color = holoGreen)
                }

            }
        }
    }




