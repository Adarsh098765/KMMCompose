
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import dev.icerock.moko.mvvm.viewmodel.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class ViewModelScreenNavi():Screen{
    @Composable
    override fun Content() {
        NavigationScreen(title = "ViewModel"){
            ViewModelScreen()
        }
    }

}

@Composable
fun ViewModelScreen(
    viewModel: SimpleViewModel = getViewModel(
        key = "simple-screen",
        factory = viewModelFactory { SimpleViewModel() }
    )
) {
    val count = viewModel.count.collectAsState()
    Box(
        modifier = Modifier.fillMaxSize(),
       contentAlignment = Alignment.Center
    ) {
        Column(verticalArrangement = Arrangement.spacedBy(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = count.value.toString())
            Button(onClick = viewModel::onCountClick) {
                Text(text = "Click on me")
            }
        }

    }


}

class SimpleViewModel : ViewModel() {
    private val _count: MutableStateFlow<Int> = MutableStateFlow(0)
    val count: StateFlow<Int> get() = _count

    init {
        println("view model $this created!")
    }

    fun onCountClick() {
        _count.update { it + 1 }
    }

    override fun onCleared() {
        super.onCleared()

        println("view model $this cleared!")
    }
}