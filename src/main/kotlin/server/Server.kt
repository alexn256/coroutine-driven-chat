package server


import kotlinx.coroutines.*
import models.Message
import models.Text
import java.io.IOException
import java.net.ServerSocket
import java.net.Socket


class Server(private val port: Int,  private val scope: CoroutineScope = CoroutineScope(Dispatchers.IO)) {

    private val socket: ServerSocket = ServerSocket(port)
    private val clientSockets:MutableMap<String, Socket> = mutableMapOf()

    suspend fun start() {
        println("The server started on port $port.")
        val job = scope.launch(CoroutineName("receiver")) {
            listen()
        }
        job.join()
    }

    private suspend fun receive() {
        if (clientSockets.isNotEmpty()) {
            clientSockets.forEach { (hostname, socket) ->
                val reader = socket.getInputStream().bufferedReader()
                scope.launch  {
                    val message = reader.readLine()
                    println("$hostname: $message")  // log message to console
                }
            }
        }
    }

    suspend fun send(message: Message<Text>) {
        //todo: send the message to particular user (Socket).
    }

    suspend fun stop() {
        try {
            withContext(Dispatchers.IO) {
                clientSockets.values.forEach { it.close() }
                socket.close()
            }
            println("The server is stopping")
        } catch (e: IOException) {
            println("Error stopping the server: ${e.message}")
        }
    }

    private suspend fun listen() {
        while (true) {
            accept()
            receive()
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
            scope.launch() {
                clientSocket.close()
            }
            println("Client connected: ${clientSocket.inetAddress.hostAddress}")
        } catch (e: IOException) {
            println("Disconnection failed: Unable to close a connection with the client. ${e.message}")
        }
    }
}