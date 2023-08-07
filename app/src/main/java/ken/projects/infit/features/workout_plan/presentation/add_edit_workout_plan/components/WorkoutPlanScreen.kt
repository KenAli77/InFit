package ken.projects.infit.features.workout_plan.presentation.add_edit_workout_plan.components


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.navigation.NavHostController
import ken.projects.infit.R
import ken.projects.infit.core.utils.customClickable
import ken.projects.infit.features.workout_plan.presentation.add_edit_workout_plan.events.button_click.WorkoutPlanClickEvent
import ken.projects.infit.ui.composables.home.Heading
import ken.projects.infit.features.workout_plan.presentation.add_edit_workout_plan.events.dialog.WorkoutPlanDialogEvent
import ken.projects.infit.features.workout_plan.presentation.add_edit_workout_plan.events.pager.WorkoutPlanPagerEvent
import ken.projects.infit.features.workout_plan.presentation.add_edit_workout_plan.events.user_input.WorkoutPlanUserInputEvent
import ken.projects.infit.features.workout_plan.presentation.add_edit_workout_plan.viewmodel.WorkoutPlanViewModel
import ken.projects.infit.ui.composables.home.Title
import ken.projects.infit.ui.theme.*

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun WorkoutPlanScreen(viewmodel: WorkoutPlanViewModel, navController: NavHostController,scaffoldState: ScaffoldState) =
    with(viewmodel) {
        val pagerState = rememberPagerState(pageCount = { state.pagerPageCount })

        val context = LocalContext.current

        LaunchedEffect(key1 = context) {
            pagerEvents.collect { event ->
                when (event) {
                is    WorkoutPlanPagerEvent.NavigateBack -> pagerState.animateScrollToPage(
                        pagerState.currentPage - 1
                    )
                is    WorkoutPlanPagerEvent.NavigateForward -> pagerState.animateScrollToPage(
                        pagerState.currentPage + 1
                    )
                }
            }
        }
        LaunchedEffect(key1 = pagerState.currentPage) {
            onPageChange(pagerState.currentPage)
        }

        LaunchedEffect(key1 = state.error){
            state.error?.let{
                scaffoldState.snackbarHostState.showSnackbar(it.getString(context))
            }
        }
        Surface(modifier = Modifier.fillMaxSize(), color = darkBlue) {

            ConstraintLayout(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(vertical = 10.dp)
            ) {
                val (header, pager, nav) = createRefs()

                Heading(
                    text = stringResource(R.string.set_up_workout_plan_heading),
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .constrainAs(header) {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                    color = holoGreen
                )
                HorizontalPager(
                    userScrollEnabled = false,
                    state = pagerState,
                    modifier = Modifier
                        .constrainAs(pager) {
                            top.linkTo(header.bottom, 20.dp)
                        }
                        .padding(vertical = 10.dp)
                ) { index ->
                    if (index == 0) {
                        WorkoutPlanSetUpPager1(
                            state = state,
                            onUserInputEvent = { onUserInputEvent(it) },
                        modifier = Modifier.padding(horizontal=10.dp))
                    }
                    if (index == 1) {
                        WorkoutPlanSetUpPager2(
                            goal = state.goal,
                            onSelected = {
                                onUserInputEvent(
                                    WorkoutPlanUserInputEvent.EnteredWorkoutGoal(it)
                                )
                            },
                            modifier = Modifier.padding(horizontal=10.dp))
                    }

//                    if (index == 2) {
//                        WorkoutPlanSetUpPager3(
//                            modifier = Modifier,
//                            openDialog = state.openExerciseDialog,
//                            onDialogChange = {onDialogEvent(WorkoutPlanDialogEvent.ExerciseDialogEvent(it))},
//                            exerciseMenuExpanded = state.exerciseMenuExpanded,
//                            onExerciseMenuChange = {onDialogEvent(WorkoutPlanDialogEvent.ExerciseMenuEvent(it))},
//                            equipmentMenuExpanded = state.equipmentMenuExpanded,
//                            onEquipmentMenuChange = {onDialogEvent(WorkoutPlanDialogEvent.EquipmentMenuEvent(it))},
//                            exercises = state.exercises,
//                            exercise = state.exercise,
//                            onExerciseSelected = {onUserInputEvent(WorkoutPlanUserInputEvent.SelectedExercise(it))},
//                            equipments = state.equipments,
//                            equipment = state.equipment,
//                            onEquipmentSelected = {onUserInputEvent(WorkoutPlanUserInputEvent.SelectedEquipment(it))},
//                            setsTotal = state.setsTotal,
//                            onSetChange = {onUserInputEvent(WorkoutPlanUserInputEvent.EnteredSetTotal(it))},
//                            onSave = {onClickEvent(WorkoutPlanClickEvent.SaveExerciseClickEvent)},
//                            exerciseList = state.exerciseItems,
//                            onRemoveExercise = {item,itemIndex -> onClickEvent(WorkoutPlanClickEvent.RemoveExerciseClickEvent(index = itemIndex,item = item))}
//
//                        )
//                    }
                }

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp)
                        .constrainAs(nav) {
                            bottom.linkTo(parent.bottom)
                        },
                ) {
                    if (state.pagerBackNavVisible) {
                        Title(text = stringResource(R.string.back).lowercase(), modifier = Modifier
                            .customClickable {
                                onPagerEvent(WorkoutPlanPagerEvent.NavigateBack(pagerState.currentPage))
                            }
                            .align(Alignment.BottomStart),
                            color = holoGreen)
                    }

                    Row(
                        Modifier
                            .align(Alignment.BottomCenter)
                            .padding(bottom = 5.dp),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        repeat(state.pagerPageCount) { iteration ->
                            val color =
                                if (pagerState.currentPage == iteration) holoGreen else blueWhite.copy(0.5f)
                            Box(
                                modifier = Modifier
                                    .padding(2.dp)
                                    .clip(CircleShape)
                                    .background(color)
                                    .size(8.dp)
                            )
                        }
                    }


                    Title(text = stringResource(state.pagerNavText).lowercase(),
                        modifier = Modifier
                            .customClickable {
                                onPagerEvent(WorkoutPlanPagerEvent.NavigateForward(pagerState.currentPage))
                            }
                            .align(Alignment.BottomEnd)
                        ,
                        color = holoGreen)
                }

            }
        }
    }






