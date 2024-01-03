import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource

@Composable
fun WelcomeScreen(route: (Route) -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colors.background)
            .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Button(onClick = { route(Route.Permissions) }) {
            Text(text = "moko-permissions")
        }

        Button(onClick = { route(Route.ViewModel) }) {
            Text(text = "moko-mvvm")
        }

        Button(onClick = { route(Route.Biometry) }) {
            Text(text = "moko-biometry")
        }


    }
}