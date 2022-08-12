package ken.projects.infit.ui.composables.home

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.rememberLottieComposition
import ken.projects.infit.R
import ken.projects.infit.ui.composables.RegularButton

@Preview
@Composable
fun EmptyWorkoutPlanView(modifier: Modifier = Modifier,user:String="", onClick: () -> Unit = {}) {

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.empty_workout_anim))


    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.Start
    ) {

        Heading(text = stringResource(R.string.welcome) +" "+user.replaceFirstChar { it.uppercase() })

        SubHeading(text = stringResource(R.string.empty_plan_text))

        LottieAnimation(
            composition = composition,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
            ,
            iterations = Int.MAX_VALUE
        )


        RegularButton(text = stringResource(R.string.get_started),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            onClick = onClick)


    }
}