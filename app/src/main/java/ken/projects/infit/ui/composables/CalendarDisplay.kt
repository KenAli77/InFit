package ken.projects.infit.ui.composables

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import ken.projects.infit.R
import ken.projects.infit.ui.theme.*
import ken.projects.infit.viewmodel.WorkoutViewModel
import java.time.DayOfWeek
import java.time.LocalDateTime
import kotlin.collections.ArrayList

@Composable
fun CalendarDisplay(
    modifier: Modifier = Modifier,
    workoutDays: ArrayList<DayOfWeek>?,
    workoutViewModel: WorkoutViewModel = viewModel()
) {

    LazyRow(
        modifier = modifier,
        contentPadding = PaddingValues(horizontal = 15.dp),
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        items(getDates()) { date ->
            workoutDays?.let { workoutDays ->

                var isSelected by remember { mutableStateOf(false) }

                val colorFilter = if (workoutDays.contains(date.dayOfWeek)) {
                    ColorFilter.tint(holoRed)
                } else {
                    ColorFilter.tint(Color.Transparent)
                }

                CalendarDisplayItem(
                    date = date,
                    colorFilter = colorFilter,
                    workoutViewModel = workoutViewModel
                )
            }
        }
    }

}

fun getDates(): ArrayList<LocalDateTime> {

    val today = LocalDateTime.now()
    val dates = ArrayList<LocalDateTime>()
    dates.add(today)
    for (i in 1..13) {
        val nextDate = today.plusDays(i.toLong())
        dates.add(nextDate)
    }

    return dates
}

@Composable
fun CalendarDisplayItem(
    modifier: Modifier = Modifier,
    date: LocalDateTime,
    colorFilter: ColorFilter,
    workoutViewModel: WorkoutViewModel = viewModel()
) {

    var isSelected by remember { mutableStateOf(false) }

    isSelected = workoutViewModel.calendarSelection.dayOfYear == date.dayOfYear


    var surfaceColor by remember {
        mutableStateOf(lightBlue)
    }

    surfaceColor = if (isSelected) holoGreen else lightBlue

    var textColor by remember {
        mutableStateOf(white)
    }

    textColor = if (isSelected) Color.Black else white

    Surface(
        color = surfaceColor,
        shape = RoundedCornerShape(40.dp),
        modifier = modifier
            .height(90.dp)
            .width(50.dp)
            .clickable {
                workoutViewModel.selectDay(date)
                workoutViewModel.getDayString(date)
            },
        border = BorderStroke(1.dp, white.copy(0.3f)),

        ) {

        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Image(
                painter = painterResource(id = R.drawable.ic_dot),
                contentDescription = "",
                colorFilter = colorFilter,
                modifier = Modifier
                    .height(10.dp)
                    .width(10.dp)
            )


            Text(
                text = date.dayOfMonth.toString(),
                fontSize = 20.sp,
                fontFamily = outfit,
                fontWeight = FontWeight.SemiBold,
                color = textColor
            )

            Text(
                text = date.dayOfWeek.name.substring(0, 3),
                fontSize = 10.sp,
                fontFamily = outfit,
                modifier = Modifier.padding(bottom = 10.dp),
                fontWeight = FontWeight.Light,
                color = textColor.copy(0.6f)
            )

        }

    }
}

