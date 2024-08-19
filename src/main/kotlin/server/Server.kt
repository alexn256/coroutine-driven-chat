package server

import java.net.ServerSocket

class Server(val port: Int) {

    val socket = ServerSocket(port)

    fun start() {

    }

    fun receive() {

    }

    fun send() {

    }

    suspend fun stop() {

    }
}