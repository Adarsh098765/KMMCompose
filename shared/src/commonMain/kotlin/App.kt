import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import co.touchlab.kermit.Logger
import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.PermissionsController
import dev.icerock.moko.permissions.compose.PermissionsControllerFactory
import dev.icerock.moko.permissions.compose.rememberPermissionsControllerFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun App() {

    var currentScreen: Route by rememberSaveable { mutableStateOf(Route.Welcome) }
    MaterialTheme {
        when (currentScreen) {
            Route.Welcome -> WelcomeScreen(route = { currentScreen = it })

            Route.Permissions -> PermissionsScreen(backAction = { currentScreen = Route.Welcome })

            Route.ViewModel -> ViewModelScreen(backAction = { currentScreen = Route.Welcome })

            Route.Biometry -> BiometryScreen(backAction = { currentScreen = Route.Welcome })

            else -> {}
        }
    }
}

expect fun getPlatformName(): String