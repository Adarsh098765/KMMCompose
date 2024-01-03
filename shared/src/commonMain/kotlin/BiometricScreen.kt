
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
import dev.icerock.moko.biometry.BiometryAuthenticator
import dev.icerock.moko.biometry.compose.BindBiometryAuthenticatorEffect
import dev.icerock.moko.biometry.compose.BiometryAuthenticatorFactory
import dev.icerock.moko.biometry.compose.rememberBiometryAuthenticatorFactory
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import dev.icerock.moko.resources.desc.desc
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class BiometricScreen :Screen{
    @Composable
    override fun Content() {
        NavigationScreen("Biometric"){
            BiometryScreen()
        }

    }

}

@Composable
internal fun BiometryScreen(
) {
    val biometryAuthenticatorFactory: BiometryAuthenticatorFactory =
        rememberBiometryAuthenticatorFactory()

    BiometryScreen(
        viewModel = getViewModel(
            key = "biometry-screen",
            factory = viewModelFactory {
                BiometryViewModel(
                    biometryAuthenticator = biometryAuthenticatorFactory.createBiometryAuthenticator()
                )
            }
        )
    )
}

@Composable
private fun BiometryScreen(
    viewModel: BiometryViewModel
) {
    BindBiometryAuthenticatorEffect(viewModel.biometryAuthenticator)
    val text: String by viewModel.result.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = text)

        Button(onClick = viewModel::onButtonClick) {
            Text(text = "Click on me")
        }
    }
}



internal class BiometryViewModel(
    val biometryAuthenticator: BiometryAuthenticator
) : ViewModel() {
    private val _result: MutableStateFlow<String> = MutableStateFlow("press button")
    val result: StateFlow<String> get() = _result

    fun onButtonClick() {
        viewModelScope.launch {
            try {
                biometryAuthenticator.checkBiometryAuthentication(
                    requestTitle = "Biometry".desc(),
                    requestReason = "Just for demo".desc(),
                    failureButtonText = "Cancel".desc()
                )
                _result.value = "biometry check success"
            } catch (exc: Exception) {
                _result.value = exc.toString()
            }
        }
    }
}
