package test

import net.solutinno.pocket.Pocket
import net.solutinno.pocket.model.AuthorizeResult
import net.solutinno.pocket.model.RequestResult
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import rx.lang.kotlin.observable
import rx.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class Authenticate : Assert() {

    val key: String = "46890-6b7d257a3df89fdc96e186f8"

    val redirect: String = "http://www.solutinno.net"

    @Before
    fun setUp() {
        Pocket.init(key)
    }

    @Test
    fun testAuthentication () {
        observable<String> { it.onNext(redirect) }
                .flatMap { Pocket.Authentication.rxRequest(it) }
                .doOnNext { checkRequest(it) }
                .doOnNext { authenticate(it.code) }
                .delay(3, TimeUnit.SECONDS, Schedulers.immediate())
                .flatMap { Pocket.Authentication.rxAuthorize(it.code) }
                .doOnNext { checkAuthorize(it) }
                .subscribe { println("Done") }
    }

    private fun checkRequest(request: RequestResult?) {
        assertNotNull(request)
        assert(request!!.error == 0)
        println(request)
    }

    private fun authenticate (code: String) {
        val command = "open ${Pocket.Authentication.getAuthorizeUrl(code, redirect)}"
        Runtime.getRuntime().exec(command)
        println(command)
    }

    private fun checkAuthorize(result: AuthorizeResult?) {
        assertNotNull(result)
        assert(result!!.error == 0)
        println(result)
    }
}
