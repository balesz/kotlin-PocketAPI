package test

import junit.framework.TestCase
import net.solutinno.pocket.Pocket
import net.solutinno.pocket.model.AddParams
import kotlin.test.assertNotNull

class Add(name: String) : TestCase(name) {

    val key: String = "46890-6b7d257a3df89fdc96e186f8"

    val token: String = "a3401a43-b36f-47e4-1139-08245d"

    override fun setUp() {
        super.setUp()
        Pocket.init(consumer_key = key, access_token = token)
    }

    fun testAddWithAuthors () {
        val result = Pocket.add(AddParams(
                url =  "http://androidportal.hu/2015-10-18/oneplus-one-malyvasitas/"))
        assertNotNull(result)
        println(result)
    }

    fun testAddWithVideos () {
        val result = Pocket.add(AddParams(
                url = "http://www.gamekapocs.hu/hir/47173/life_is_strange_trailer_az_evadfinalera"))
        assertNotNull(result)
        println(result)
    }
}
