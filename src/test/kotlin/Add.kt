import junit.framework.TestCase
import net.solutinno.pocket.Pocket

class Add(name: String?) : TestCase(name) {

    val key: String = "46890-6b7d257a3df89fdc96e186f8"

    val token: String = "a3401a43-b36f-47e4-1139-08245d"

    override fun setUp() {
        super.setUp()
        Pocket.init(key)
    }

    fun testAddWithAuthors () {
        val url: String = "http://androidportal.hu/2015-10-18/oneplus-one-malyvasitas/"
        val result = Pocket.Add.add(token, url)
        println(result)
        assert(result.error == 0)
    }

    fun testAddWithVideos () {
        val url: String = "http://www.gamekapocs.hu/hir/47173/life_is_strange_trailer_az_evadfinalera"
        val result = Pocket.Add.add(token, url)
        println(result)
        assert(result.error == 0)
    }
}
