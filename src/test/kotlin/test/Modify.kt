package test

import junit.framework.TestCase
import junit.framework.TestSuite
import net.solutinno.pocket.Pocket
import net.solutinno.pocket.model.*
import test.extensions.add
import kotlin.reflect.KCallable

class Modify(name: String) : TestCase(name) {

    constructor (func: KCallable<*>) : this(func.name)

    val key: String = "46890-6b7d257a3df89fdc96e186f8"

    val token: String = "a3401a43-b36f-47e4-1139-08245d"

    val item1 = "1077188003"
    val item2 = "1076110709"
    val tag1 = "tag1"
    val tag2 = "tag2"

    override fun setUp() {
        super.setUp()
        Pocket.init(consumer_key = key, access_token = token)
    }

    companion object {

        @JvmStatic fun suite () : TestSuite = TestSuite().apply {
            add(modifyTest, tagTest)
        }

        val modifyTest: TestSuite = TestSuite().apply {
            addTest(Modify(Modify::delete))
            addTest(Modify(Modify::add))
            addTest(Modify(Modify::archive))
            addTest(Modify(Modify::readd))
            addTest(Modify(Modify::favorite))
            addTest(Modify(Modify::unfavorite))
        }

        val tagTest: TestSuite = TestSuite().apply {
            addTest(Modify(Modify::add))
            addTest(Modify(Modify::tags_add))
            addTest(Modify(Modify::tags_remove))
            addTest(Modify(Modify::tags_add))
            addTest(Modify(Modify::tags_replace))
            addTest(Modify(Modify::tag_rename))
            addTest(Modify(Modify::tags_clear))
        }
    }

    fun add () {
        assertTest(Pocket.Actions.Builder().apply {
            add (ActionAddParams(item_id = item1))
            add (ActionAddParams(item_id = item2))
        }.send())
    }

    fun archive () {
        assertTest(Pocket.Actions.Builder().apply {
            archive (ActionBasicParams(item_id = item1))
            archive (ActionBasicParams(item_id = item2))
        }.send())
    }

    fun readd () {
        assertTest(Pocket.Actions.Builder().apply {
            readd (ActionBasicParams(item_id = item1))
            readd (ActionBasicParams(item_id = item2))
        }.send())
    }

    fun favorite () {
        assertTest(Pocket.Actions.Builder().apply {
            favorite (ActionBasicParams(item_id = item1))
            favorite (ActionBasicParams(item_id = item2))
        }.send())
    }

    fun unfavorite () {
        assertTest(Pocket.Actions.Builder().apply {
            unfavorite (ActionBasicParams(item_id = item1))
            unfavorite (ActionBasicParams(item_id = item2))
        }.send())
    }

    fun delete () {
        assertTest(Pocket.Actions.Builder().apply {
            delete (ActionBasicParams(item_id = item1))
            delete (ActionBasicParams(item_id = item2))
        }.send())
    }

    fun tags_add () {
        assertTest(Pocket.Actions.Builder().apply {
            tags_add(ActionTagsParams(item_id = item1, tags = tag1))
            tags_add(ActionTagsParams(item_id = item2, tags = tag2))
        }.send())
    }

    fun tags_remove () {
        assertTest(Pocket.Actions.Builder().apply {
            tags_remove(ActionTagsParams(item_id = item1, tags = tag1))
            tags_remove(ActionTagsParams(item_id = item2, tags = tag2))
        }.send())
    }

    fun tags_replace () {
        assertTest(Pocket.Actions.Builder().apply {
            tags_replace(ActionTagsParams(item_id = item1, tags = tag2))
            tags_replace(ActionTagsParams(item_id = item2, tags = tag1))
        }.send())
    }

    fun tags_clear () {
        assertTest(Pocket.Actions.Builder().apply {
            tags_clear (ActionBasicParams(item_id = item1))
            tags_clear (ActionBasicParams(item_id = item2))
        }.send())
    }

    fun tag_rename () {
        assertTest(Pocket.Actions.Builder().apply {
            tag_rename(ActionTagParams(old_tag = tag1, new_tag = "new$tag1"))
            tag_rename(ActionTagParams(old_tag = tag2, new_tag = "new$tag2"))
        }.send())
    }

    private fun assertTest (result: SendResult?) {
        assertNotNull(result)
        assertNotNull(result?.action_results)
        for (res in result!!.action_results!!) {
            assert(res.addResult != null || res.basicResult != null)
            if (res.addResult != null)
                assert(!res.addResult?.item_id.isNullOrBlank())
            else if (res.basicResult != null)
                assert(res.basicResult!!)
        }
    }
}
