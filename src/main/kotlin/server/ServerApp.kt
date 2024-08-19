package server

fun main() {
    val port = 9000
    val server = Server(port)
    server.start()
}