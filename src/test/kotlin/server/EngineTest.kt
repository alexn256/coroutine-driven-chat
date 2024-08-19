package server

import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class EngineTest {

    val engine = Engine

    @Test
    fun `Socket connection test` () {
        var connect = engine.connect(8080)
        println(connect)
        assertNotNull(connect)
    }
}