package models

import java.time.LocalDateTime

enum class Status {
    ONLINE, OFFLINE
}

data class User(
    val id: String,
    val username: String,
    val lastName: String,
    val firstName: String,
    val status: Status
)

data class Message <T> (
    val id: String,
    val payload: T,
    val createdAt: LocalDateTime,
    val sender: String
)