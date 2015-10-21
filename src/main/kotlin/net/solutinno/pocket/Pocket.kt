package net.solutinno.pocket

import com.squareup.moshi.Moshi
import net.solutinno.pocket.adapters.*
import net.solutinno.pocket.interfaces.PocketInterface
import net.solutinno.pocket.model.*
import retrofit.MoshiConverterFactory
import retrofit.Retrofit

object Pocket {

    private val url: String = "https://getpocket.com"

    private lateinit var consumer_key: String

    fun init(consumer_key: String) {
        this.consumer_key = consumer_key
    }

    private val moshi: Moshi
        get() = Moshi.Builder()
                .add(AuthorsJsonAdapter())
                .add(ImagesJsonAdapter())
                .add(VideosJsonAdapter())
                .build()

    private val pocketInterface: PocketInterface
        get() = Retrofit.Builder().baseUrl(Pocket.url)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build().create(PocketInterface::class.java)

    object Authentication {
        fun request (redirect_uri: String, state: String = "") : RequestResult {
            val call = pocketInterface.request(RequestParams(consumer_key, redirect_uri, state))
            val response = call.execute()
            val result = response.body()
            return result?: RequestResult("", "", response.headers().get("X-Error-Code").toInt())
        }

        fun getAuthorizeUrl (code: String, redirect_uri: String) : String {
            return "https://getpocket.com/auth/authorize?request_token=$code&redirect_uri=$redirect_uri"
        }

        fun authorize (code: String) : AuthorizeResult {
            val call = pocketInterface.authorize(AuthorizeParams(consumer_key, code))
            val response = call.execute()
            val result = response.body()
            return result?: AuthorizeResult("", "", response.headers().get("X-Error-Code").toInt())
        }
    }

    fun add (access_token: String, init: AddParams.() -> Unit) : AddResult? {
        val params = AddParams(consumer_key, access_token)
        params.init()
        val call = pocketInterface.add(params)
        val response = call.execute()
        val result = response.body()
        return result
    }

    fun retrieve(access_token: String, init: RetrieveParams.() -> Unit) : RetrieveResult {
        val params = RetrieveParams(consumer_key, access_token)
        params.init()
        val call = pocketInterface.retrieve(params)
        val response = call.execute()
        val result = response.body()
        return result?:RetrieveResult()
    }
}
