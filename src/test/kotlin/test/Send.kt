package test

import junit.framework.TestCase
import net.solutinno.pocket.Pocket
import java.util.*
import kotlin.test.assertNotNull

class Send(name: String) : TestCase(name) {

    val key: String = "46890-6b7d257a3df89fdc96e186f8"

    val token: String = "a3401a43-b36f-47e4-1139-08245d"

    fun test () {
        Pocket.init(key)
        val result = Pocket.Actions.Builder(token).apply {
            delete {
                item_id = "1077188003"
                time = Date().time
            }
            delete ("1076110709") {
                time = Date().time
            }
        }.send()
        assertNotNull(result)
        println(result)
    }
}
