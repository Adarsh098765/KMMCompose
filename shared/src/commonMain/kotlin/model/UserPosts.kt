package model

import kotlinx.serialization.Serializable

@Serializable
data class UserPosts(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)
