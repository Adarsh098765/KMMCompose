import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import dev.icerock.moko.mvvm.compose.getViewModel
import dev.icerock.moko.mvvm.compose.viewModelFactory
import model.UserPosts
import viewmodel.TestViewModel

class KtorScreenNavi():Screen {
    @Composable
    override fun Content() {
        val viewModel: TestViewModel = getViewModel(
            key = "simple-screen",
            factory = viewModelFactory { TestViewModel() }
        )
        val uiState by viewModel.uiState.collectAsState()
        NavigationScreen("Ktor Api"){
            LazyColumn (
                verticalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier.fillMaxSize().padding(horizontal = 5.dp),
                content = {
                    items(uiState.posts,key = {it.id}) {
                        PostView(it)
                    }
                }
            )
        }
    }
}
@Composable
fun PostView(userPost: UserPosts) {
    Column(){
        Text(userPost.title)
        Spacer(Modifier.height(8.dp))
        Text(userPost.body)

    }

}