package server

import kotlinx.coroutines.runBlocking


fun main() = runBlocking {
    val server = Server(8000)
    server.start()
}