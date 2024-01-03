
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import co.touchlab.kermit.Logger
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import dev.icerock.moko.permissions.DeniedException
import dev.icerock.moko.permissions.Permission
import dev.icerock.moko.permissions.PermissionsController
import dev.icerock.moko.permissions.RequestCanceledException
import dev.icerock.moko.permissions.compose.BindEffect
import dev.icerock.moko.permissions.compose.PermissionsControllerFactory
import dev.icerock.moko.permissions.compose.rememberPermissionsControllerFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PermissionScreenNavi():Screen{
    @Composable
    override fun Content() {
        NavigationScreen("Permissions"){
            PermissionsScreen()
        }

    }

}

@Composable
internal fun PermissionsScreen(
) {
    val permissionsControllerFactory: PermissionsControllerFactory =
        rememberPermissionsControllerFactory()

    PermissionsScreen(
        viewModel = getViewModel(
            key = "permissions-screen",
            factory = viewModelFactory {
                PermissionsViewModel(permissionsControllerFactory.createPermissionsController())
            }
        )
    )
}

@Composable
private fun PermissionsScreen(
    viewModel: PermissionsViewModel
){
    BindEffect(viewModel.permissionsController)

    val state: String by viewModel.state.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = state)

        Button(onClick = viewModel::onButtonClick) {
            Text(text = "Click on me")
        }
    }
}


internal class PermissionsViewModel(
    val permissionsController: PermissionsController
) : ViewModel() {
    private val _state: MutableStateFlow<String> = MutableStateFlow("press button")
    val state: StateFlow<String> get() = _state

    fun onButtonClick() {
        viewModelScope.launch {
            try {
                permissionsController.providePermission(Permission.LOCATION)
                _state.value = "permission granted"
                Logger.e( "Click on me", tag = "moko-permissions")
            } catch (exc: RequestCanceledException) {
                _state.value = "permission cancelled $exc"
            } catch (exc: DeniedException) {
                _state.value = "permission denied $exc"
            }
        }
    }
}