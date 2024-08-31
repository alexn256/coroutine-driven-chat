package server


import kotlinx.coroutines.*
import models.Message
import models.Text
import java.io.IOException
import java.net.ServerSocket
import java.net.Socket

data class Client(val socket: Socket, val name: String)

class Server(private val port: Int) {

    private val socket: ServerSocket = ServerSocket(port)
    private val clientSockets:MutableMap<String, Socket> = mutableMapOf()

    suspend fun start() {
        println("The server started on port $port.")
        val job = CoroutineScope(Dispatchers.IO).launch(CoroutineName("receiver")) {
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

    private suspend fun listen() {
        while (true) {
            accept()
        }
    }

    private suspend fun accept() {
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

    suspend fun close(clientSocket: Socket) {
        try {
            withContext(Dispatchers.IO) {
                clientSocket.close()
            }
            println("Client connected: ${clientSocket.inetAddress.hostAddress}")
        } catch (e: IOException) {
            println("Disconnection failed: Unable to close a connection with the client. ${e.message}")
        }
    }
}