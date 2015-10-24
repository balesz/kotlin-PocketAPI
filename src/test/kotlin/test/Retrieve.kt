package test

import junit.framework.TestCase
import net.solutinno.pocket.Pocket
import net.solutinno.pocket.model.RetrieveParams

class Retrieve(name: String) : TestCase(name) {

    val key: String = "46890-6b7d257a3df89fdc96e186f8"

    val token: String = "a3401a43-b36f-47e4-1139-08245d"

    override fun setUp() {
        super.setUp()
        Pocket.init(consumer_key = key, access_token = token)
    }

    fun test () {
        val result = Pocket.retrieve(RetrieveParams(
                count = 2,
                state = RetrieveParams.STATE_UNREAD,
                detailType = RetrieveParams.DETAIL_TYPE_SIMPLE))
        checkNotNull(result)
        for (item in result.list)
            println(item)
    }
}
