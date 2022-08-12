package ken.projects.infit.ui.composables.profile

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import ken.projects.infit.ui.composables.home.Title
import ken.projects.infit.ui.theme.darkBlue

@Composable
fun ProfileScreen() {

    Surface(modifier = Modifier.fillMaxSize(), color = darkBlue) {

        Title(text = "coming soon", modifier = Modifier.fillMaxSize())

    }

}