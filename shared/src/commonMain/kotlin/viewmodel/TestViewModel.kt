package viewmodel

import dev.icerock.moko.mvvm.viewmodel.ViewModel
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import model.UserPosts

data class PostUiState(
    val posts: List<UserPosts> = emptyList(),
)
class TestViewModel:ViewModel() {
    private val _uiState = MutableStateFlow(PostUiState())
    val uiState = _uiState.asStateFlow()

    init {
        updatePosts()
    }

    private val httpClient = HttpClient {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
    }
    private fun updatePosts() {
        viewModelScope.launch {
            val posts = getPosts()
            _uiState.update {
                it.copy(posts = posts)
            }
        }
    }
    private suspend fun getPosts(): List<UserPosts> {
        return httpClient.get("https://jsonplaceholder.typicode.com/posts").body<List<UserPosts>>()
    }
    override fun onCleared() {
        httpClient.close()
    }
}