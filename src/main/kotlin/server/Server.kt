package server

import models.Message
import models.Text
import java.net.ServerSocket
import java.net.Socket

class Server(private val port: Int) {

    val socket = ServerSocket(port)
    val clientSockets = mutableMapOf<String, Socket>()

    fun start() {
        println("The server started on port $port.")
    }

    suspend fun receive(): Message<Text> {

    }

    suspend fun send(message: Message<Text>) {

    }

    suspend fun stop() {

    }
}