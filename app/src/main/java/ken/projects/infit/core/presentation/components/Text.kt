package ken.projects.infit.core.presentation.components

import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ken.projects.infit.ui.theme.outfit
import ken.projects.infit.ui.theme.white

@Composable
fun Heading(text: String, modifier: Modifier = Modifier, color: Color = white) {
    Text(
        text = text,
        fontWeight = FontWeight.Bold,
        fontSize = 30.sp,
        fontFamily = outfit,
        modifier = modifier.paddingFromBaseline(top = 16.dp),
        color = color
    )
}

@Composable
fun SubHeading(text: String, modifier: Modifier = Modifier, color: Color = white) {
    Text(
        text = text,
        fontWeight = FontWeight.SemiBold,
        fontSize = 28.sp,
        fontFamily = outfit,
        modifier = modifier.paddingFromBaseline(top = 16.dp),
        color = color
    )
}

@Composable
fun Title(text: String, modifier: Modifier = Modifier, color: Color = white, fontWeight: FontWeight = FontWeight.Normal) {
    Text(
        text = text,
        fontWeight = fontWeight,
        fontFamily = outfit,
        fontSize = 25.sp,
        color = color,
        modifier = modifier
    )
}

@Composable
fun Paragraph(text: String, modifier: Modifier = Modifier, color: Color = white, fontWeight: FontWeight = FontWeight.Light) {
    Text(
        text = text,
        fontWeight = fontWeight,
        fontFamily = outfit,
        fontSize = 20.sp,
        color = color,
        modifier = modifier
    )
}