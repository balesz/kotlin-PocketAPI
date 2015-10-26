package test

import junit.framework.Test
import junit.framework.TestCase
import junit.framework.TestSuite
import net.solutinno.pocket.Pocket
import net.solutinno.pocket.model.AddParams
import net.solutinno.pocket.model.ErrorCode
import net.solutinno.pocket.model.PocketError
import test.extensions.add
import kotlin.reflect.KCallable

class Add(name: String) : TestCase(name) {

    constructor (func: KCallable<*>) : this(func.name)

    val key: String = "46890-6b7d257a3df89fdc96e186f8"

    val token: String = "a3401a43-b36f-47e4-1139-08245d"

    val urlWithAuthor = "http://androidportal.hu/2015-10-18/oneplus-one-malyvasitas/"

    val urlWithVideo = "http://www.gamekapocs.hu/hir/47173/life_is_strange_trailer_az_evadfinalera"

    companion object {

        @JvmStatic fun suite () : Test = tests

        private val tests: TestSuite get() = TestSuite().apply {
            add(errorTests, contentTests)
        }

        private val contentTests: TestSuite get() = TestSuite().apply {
            addTest(Add(Add::addWithAuthors))
            addTest(Add(Add::addWithVideos))
        }

        private val errorTests: TestSuite get() = TestSuite().apply {
            addTest(Add(Add::addWithoutKey))
            addTest(Add(Add::addWithoutToken))
            addTest(Add(Add::addEmptyUrl))
            addTest(Add(Add::addBlankUrl))
            addTest(Add(Add::addInvalidUrl))
        }
    }

    override fun setUp() {
        super.setUp()
        Pocket.init(consumer_key = key, access_token = token)
    }

    //region Error Tests

    fun addWithoutKey () {
        Pocket.init(consumer_key = "")
        try { addWithAuthors() }
        catch (error: PocketError) { assert(error.errorCode == ErrorCode.CONSUMER_KEY_NOT_FOUND) }
    }

    fun addWithoutToken () {
        Pocket.init(consumer_key = key)
        try { addWithAuthors() }
        catch (error: PocketError) { assert(error.errorCode == ErrorCode.INVALID_TOKEN) }
    }

    fun addEmptyUrl () {
        try { Pocket.add(AddParams(url = "")) }
        catch (error: PocketError) { assert(error.errorCode == ErrorCode.POCKET_SERVER_ERROR) }
    }

    fun addBlankUrl () {
        try { Pocket.add(AddParams(url = "   ")) }
        catch (error: PocketError) { assert(error.errorCode == ErrorCode.INVALID_REQUEST) }
    }

    fun addInvalidUrl () {
        try { Pocket.add(AddParams(url = "Invalid Url")) }
        catch (error: PocketError) { assert(error.errorCode == ErrorCode.INVALID_REQUEST) }
    }

    //endregion

    //region Content Tests

    fun addWithAuthors () {
        val result = Pocket.add(AddParams(url = urlWithAuthor))
        assertNotNull(result)
        assertNotNull(result!!.item)
        assertNotNull(result.item!!.item_id)
        assertNotNull(result.item!!.authors)
        assert(result.item!!.authors!!.isNotEmpty())
    }

    fun addWithVideos () {
        val result = Pocket.add(AddParams(url = urlWithVideo))
        assertNotNull(result)
        assertNotNull(result!!.item)
        assertNotNull(result.item!!.item_id)
        assertNotNull(result.item!!.videos)
        assert(result.item!!.videos!!.isNotEmpty())
    }

    //endregion
}
