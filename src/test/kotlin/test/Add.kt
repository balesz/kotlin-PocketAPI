package test

import junit.framework.TestCase
import net.solutinno.pocket.Pocket
import kotlin.test.assertNotNull

class Add(name: String) : TestCase(name) {

    val key: String = "46890-6b7d257a3df89fdc96e186f8"

    val token: String = "a3401a43-b36f-47e4-1139-08245d"

    fun testAddWithAuthors () {
        Pocket.init(key)
        val result = Pocket.add(token) {
            url =  "http://androidportal.hu/2015-10-18/oneplus-one-malyvasitas/"
        }
        assertNotNull(result)
        println(result)
    }

    fun testAddWithVideos () {
        Pocket.init(key)
        val result = Pocket.add(token) {
            url = "http://www.gamekapocs.hu/hir/47173/life_is_strange_trailer_az_evadfinalera"
        }
        assertNotNull(result)
        println(result)
    }
}
