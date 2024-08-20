package server


import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import models.Message
import models.Text
import java.io.IOException
import java.net.ServerSocket
import java.net.Socket

data class Client(val socket: Socket, val name: String)

class Server(private val port: Int) {

    private val socket: ServerSocket
    private val clientSockets:MutableMap<String, Socket>

    init {
        socket = ServerSocket(port)
        clientSockets = mutableMapOf()
    }

    suspend fun start() {
        println("The server started on port $port.")
        val job = CoroutineScope(Dispatchers.IO).launch {
            listen()
        }
        job.join()
    }

    suspend fun receive() {

    }

    suspend fun send(message: Message<Text>) {

    }

    suspend fun stop() {

    }

    suspend fun listen() {
        while (true) {
            accept()
        }
    }

    suspend fun accept() {
        try {
            val clientSocket = withContext(Dispatchers.IO) {
                socket.accept()
            }
            val host = clientSocket.inetAddress.hostAddress
            clientSockets[host] = clientSocket
            println("Client connected: $host")
        } catch (e: IOException) {
            println("Connection failed: Unable to establish a connection to the client. ${e.message}")
        }
    }
}