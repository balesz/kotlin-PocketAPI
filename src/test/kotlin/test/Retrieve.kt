package test

import junit.framework.TestCase
import net.solutinno.pocket.Pocket
import net.solutinno.pocket.model.RetrieveParams

class Retrieve(name: String) : TestCase(name) {

    val key: String = "46890-6b7d257a3df89fdc96e186f8"

    val token: String = "a3401a43-b36f-47e4-1139-08245d"

    fun test () {
        Pocket.init(key)
        val result = Pocket.retrieve(token) {
            count = 1.toString()
            state = RetrieveParams.STATE_UNREAD
            detailType = RetrieveParams.DETAIL_TYPE_COMPLETE
        }
        checkNotNull(result)
        println(result)
    }
}
