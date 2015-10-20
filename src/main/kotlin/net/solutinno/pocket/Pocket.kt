package net.solutinno.pocket

import com.squareup.moshi.Moshi
import net.solutinno.pocket.adapters.*
import net.solutinno.pocket.interfaces.AddInterface
import net.solutinno.pocket.interfaces.AuthenticationInterface
import net.solutinno.pocket.model.*
import retrofit.MoshiConverterFactory
import retrofit.Retrofit

object Pocket {

    private val url: String = "https://getpocket.com"

    private lateinit var consumer_key: String

    fun init(consumer_key: String) {
        this.consumer_key = consumer_key
    }

    object Authentication {
        private val authInterface: AuthenticationInterface
            get() = Retrofit.Builder().baseUrl(Pocket.url)
                    .addConverterFactory(MoshiConverterFactory.create(Moshi.Builder().build()))
                    .build().create(AuthenticationInterface::class.java)

        fun request (redirect_uri: String, state: String = "") : RequestResult {
            val call = authInterface.request(RequestParams(consumer_key, redirect_uri, state))
            val response = call.execute()
            val result = response.body()
            return result?: RequestResult("", "", response.headers().get("X-Error-Code").toInt())
        }

        fun getAuthorizeUrl (code: String, redirect_uri: String) : String {
            return "https://getpocket.com/auth/authorize?request_token=$code&redirect_uri=$redirect_uri"
        }

        fun authorize (code: String) : AuthorizeResult {
            val call = authInterface.authorize(AuthorizeParams(consumer_key, code))
            val response = call.execute()
            val result = response.body()
            return result?: AuthorizeResult("", "", response.headers().get("X-Error-Code").toInt())
        }
    }

    object Add {
        private val moshi: Moshi
            get() = Moshi.Builder()
                    .add(AuthorsJsonAdapter())
                    .add(ImagesJsonAdapter())
                    .add(VideosJsonAdapter())
                    .build()
        private val addInterface: AddInterface
            get() = Retrofit.Builder().baseUrl(Pocket.url)
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .build().create(AddInterface::class.java)

        fun add (access_token: String, url: String,title: String = "", tags: String = "", tweet_id: String = "")
                : AddResult {
            val call = addInterface.add(AddParams(consumer_key, access_token, url, title, tags, tweet_id))
            val response = call.execute()
            val result = response.body()
            return result?:AddResult()
        }
    }
}
