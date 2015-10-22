package test

import junit.framework.Test
import junit.framework.TestCase
import junit.framework.TestSuite
import net.solutinno.pocket.Pocket
import net.solutinno.pocket.model.SendResult
import kotlin.test.assertNotNull

class Modify(name: String) : TestCase(name) {

    val key: String = "46890-6b7d257a3df89fdc96e186f8"

    val token: String = "a3401a43-b36f-47e4-1139-08245d"

    val item1 = "1077188003"
    val item2 = "1076110709"
    val tag1 = "tag1"
    val tag2 = "tag2"

    companion object {

        @JvmStatic fun suite () : Test = tagTest

        val modifyTest: Test = TestSuite().apply {
            addTest(Modify(Modify::delete.name))
            addTest(Modify(Modify::add.name))
            addTest(Modify(Modify::archive.name))
            addTest(Modify(Modify::readd.name))
            addTest(Modify(Modify::favorite.name))
            addTest(Modify(Modify::unfavorite.name))
        }

        val tagTest: Test = TestSuite().apply {
            addTest(Modify(Modify::add.name))
            addTest(Modify(Modify::tags_add.name))
            addTest(Modify(Modify::tags_remove.name))
            addTest(Modify(Modify::tags_add.name))
            addTest(Modify(Modify::tags_replace.name))
            addTest(Modify(Modify::tag_rename.name))
            addTest(Modify(Modify::tags_clear.name))
        }
    }

    fun assertTest (result: SendResult?) {
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

    fun add () {
        Pocket.init(key)
        assertTest(Pocket.Actions.Builder(token).apply {
            add { item_id = item1 }
            add (item2) {  }
        }.send())
    }

    fun archive () {
        Pocket.init(key)
        assertTest(Pocket.Actions.Builder(token).apply {
            archive { item_id = item1 }
            archive (item2) {  }
        }.send())
    }

    fun readd () {
        Pocket.init(key)
        assertTest(Pocket.Actions.Builder(token).apply {
            readd { item_id = item1 }
            readd (item2) {  }
        }.send())
    }

    fun favorite () {
        Pocket.init(key)
        assertTest(Pocket.Actions.Builder(token).apply {
            favorite { item_id = item1 }
            favorite (item2) {  }
        }.send())
    }

    fun unfavorite () {
        Pocket.init(key)
        assertTest(Pocket.Actions.Builder(token).apply {
            unfavorite { item_id = item1 }
            unfavorite (item2) {  }
        }.send())
    }

    fun delete () {
        Pocket.init(key)
        assertTest(Pocket.Actions.Builder(token).apply {
            delete { item_id = item1 }
            delete (item2) {  }
        }.send())
    }

    fun tags_add () {
        Pocket.init(key)
        assertTest(Pocket.Actions.Builder(token).apply {
            tags_add {
                item_id = item1
                tags = tag1
            }
            tags_add (item2) { tags = tag2 }
        }.send())
    }

    fun tags_remove () {
        Pocket.init(key)
        assertTest(Pocket.Actions.Builder(token).apply {
            tags_remove {
                item_id = item1
                tags = tag1
            }
            tags_remove (item2) { tags = tag2 }
        }.send())
    }

    fun tags_replace () {
        Pocket.init(key)
        assertTest(Pocket.Actions.Builder(token).apply {
            tags_replace {
                item_id = item1
                tags = tag2
            }
            tags_replace (item2) { tags = tag1 }
        }.send())
    }

    fun tags_clear () {
        Pocket.init(key)
        assertTest(Pocket.Actions.Builder(token).apply {
            tags_clear { item_id = item1 }
            tags_clear (item2) {  }
        }.send())
    }

    fun tag_rename () {
        Pocket.init(key)
        assertTest(Pocket.Actions.Builder(token).apply {
            tag_rename {
                old_tag = tag1
                new_tag = "new$tag1"
            }
            tag_rename {
                old_tag = tag2
                new_tag = "new$tag2"
            }
        }.send())
    }
}
