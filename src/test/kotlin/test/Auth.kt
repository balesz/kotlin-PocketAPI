package test

import junit.framework.TestCase
import net.solutinno.pocket.Pocket

class Auth(name: String?) : TestCase(name) {

    val key: String = "46890-6b7d257a3df89fdc96e186f8"

    val redirect: String = "http://www.solutinno.net"

    override fun setUp() {
        super.setUp()
        Pocket.init(key)
    }

    fun testAuthentication () {
        val result = Pocket.Authentication.request(redirect)
        println(result)
        assert(result.error == 0)
        println(Pocket.Authentication.getAuthorizeUrl(result.code, redirect))
    }

    fun testAuthorization () {
        val result = Pocket.Authentication.authorize("cc4d1f91-b127-1057-3398-43a8db")
        println(result)
        assert(result.error == 0)
    }
}
