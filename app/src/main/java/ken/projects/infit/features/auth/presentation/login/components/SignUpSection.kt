package ken.projects.infit.features.auth.presentation.login.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import ken.projects.infit.R
import ken.projects.infit.ui.theme.holoGreen
import ken.projects.infit.ui.theme.outfit
import ken.projects.infit.ui.theme.white

@Composable
fun SignUpSection(modifier: Modifier = Modifier) {

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
    ) {

        Text(
            text = stringResource(R.string.sign_up_text),
            fontWeight = FontWeight.Normal,
            color = white,
            fontFamily = outfit
        )
        Spacer(modifier = Modifier.width(5.dp))
        Text(
            text = stringResource(R.string.sign_up),
            fontWeight = FontWeight.Bold,
            color = holoGreen,
            fontFamily = outfit
        )
    }
}