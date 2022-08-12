package ken.projects.infit.ui.composables.stats

import android.graphics.PointF
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import ken.projects.infit.R
import ken.projects.infit.data.models.ExerciseHistoryItem
import ken.projects.infit.ui.composables.home.Heading
import ken.projects.infit.ui.theme.darkBlue
import ken.projects.infit.ui.theme.holoGreen
import ken.projects.infit.ui.theme.veryDarkBlue
import ken.projects.infit.ui.theme.white
import ken.projects.infit.viewmodel.WorkoutViewModel
import java.text.SimpleDateFormat
import java.util.*

@Preview
@Composable
fun StatsDetailScreen(
    navController: NavHostController = rememberNavController(),
    workoutViewModel: WorkoutViewModel = viewModel()
) = with(workoutViewModel) {

    getHistoryDataDetails()

    Surface(modifier = Modifier.fillMaxSize(), color = darkBlue) {

        Column(
            Modifier
                .fillMaxSize()
                .padding(top = 20.dp),
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            Heading(text = currentExerciseId, modifier = Modifier.padding(horizontal = 15.dp))
            ChartView(modifier = Modifier, chartData.toList())
            Heading(
                text = stringResource(R.string.log),
                modifier = Modifier.padding(horizontal = 15.dp)
            )
            StatsDetailsColumn(modifier = Modifier, historyData.toList())


        }

    }

}


@Composable
fun StatsDetailsColumn(modifier: Modifier = Modifier, historyData: List<ExerciseHistoryItem>) {
    LazyColumn(
        modifier = modifier
            .background(
                veryDarkBlue
            ), contentPadding = PaddingValues(vertical = 10.dp)
    ) {
        items(historyData) { data ->
            StatsDetailsItem(data)
            Divider(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth(), color = Color.Gray.copy(0.1f)
            )
        }
    }
}

@Composable
fun StatsDetailsItem(data: ExerciseHistoryItem) {
    Surface(
        color = veryDarkBlue,
        shape = RectangleShape,
    ) {

        Column(
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 15.dp, vertical = 10.dp)
        ) {

            val sdf = SimpleDateFormat("dd-MM-yy", Locale.ENGLISH)
            val date = sdf.format(data.date).toString()

            Text(
                text = date,
                fontWeight = FontWeight.SemiBold,
                color = holoGreen,
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(10.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth(),
            ) {


                Column(Modifier.fillMaxWidth()) {

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()

                    ) {
                        Text(
                            text = stringResource(R.string.sets_stat) + data.totalSets,
                            color = white,
                            fontWeight = FontWeight.Normal,
                            fontSize = 20.sp
                        )
                        Text(
                            text = stringResource(R.string.min_weight_stat) + data.minWeight,
                            color = white,
                            fontWeight = FontWeight.Normal,
                            fontSize = 20.sp
                        )
                    }

                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(R.string.reps_stat) + data.totalReps,
                            color = white,
                            fontWeight = FontWeight.Normal,
                            fontSize = 20.sp


                        )
                        Text(
                            text = stringResource(R.string.max_weight_stat) + data.maxWeight,
                            color = white,
                            fontWeight = FontWeight.Normal,
                            fontSize = 20.sp
                        )
                    }

                }


            }
        }


    }

}

@Composable
fun ChartView(modifier: Modifier = Modifier, chartData: List<Double>) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        elevation = 10.dp,
        color = veryDarkBlue
    ) {
        Column(
            modifier = Modifier
                .wrapContentSize(align = Alignment.BottomStart)
        ) {
            Canvas(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            ) {
                val distance = size.width / (chartData.size + 1)
                var currentX = 0F
                val maxValue = chartData.maxOrNull() ?: 0
                val points = mutableListOf<PointF>()

                chartData.forEachIndexed { index, data ->
                    if (chartData.size >= index + 2) {
                        val y0 = (maxValue.toDouble() - data) * (size.height / maxValue.toDouble())
                        val x0 = currentX + distance
                        points.add(PointF(x0, y0.toFloat()))
                        currentX += distance
                    }
                }

                for (i in 0 until points.size - 1) {
                    drawLine(
                        start = Offset(points[i].x, points[i].y),
                        end = Offset(points[i + 1].x, points[i + 1].y),
                        color = holoGreen,
                        strokeWidth = 8f,
                        pathEffect = PathEffect.cornerPathEffect(4.dp.toPx())

                    )
                }
            }
        }
    }
}

