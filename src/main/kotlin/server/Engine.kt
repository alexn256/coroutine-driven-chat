package server

import java.net.ServerSocket
import java.net.Socket

object Engine {

    fun connect(port: Int): String {
        return "connection to server on $port port."
    }
}