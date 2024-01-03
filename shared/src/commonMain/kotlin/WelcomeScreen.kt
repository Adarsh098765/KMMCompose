
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
class HomeScreen :Screen{
    @Composable
    override fun Content() {
        WelcomeScreen()
    }

}
@Composable
fun WelcomeScreen() {
    val localNavigator = LocalNavigator.currentOrThrow
    Scaffold( topBar = {
        TopAppBar(
            title = { Text(text = "Home") },
            navigationIcon = {
                IconButton(onClick = { /* Handle back navigation */ }) {
                    Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                }
            }
        )
    }) {
        Column(
            modifier = Modifier.fillMaxSize().background(color = MaterialTheme.colors.background)
                .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(onClick = {
                localNavigator.push(PermissionScreenNavi())
            }) {
                Text(text = "Moko-permissions")
            }

            Button(onClick = {
                localNavigator.push(ViewModelScreenNavi())
            }) {
                Text(text = "Moko-Mvvm")
            }

            Button(onClick = {
                localNavigator.push(BiometricScreen())

            }) {
                Text(text = "Moko-Biometric")
            }
            Button(onClick = {
                localNavigator.push(KtorScreenNavi())

            }) {
                Text(text = "Fetch-API-Result")
            }

        }
    }





    }

