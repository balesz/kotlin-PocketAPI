package net.solutinno.pocket

import com.squareup.moshi.Moshi
import com.squareup.okhttp.Headers
import net.solutinno.pocket.adapters.*
import net.solutinno.pocket.model.*
import retrofit.MoshiConverterFactory
import retrofit.Retrofit
import java.net.URLEncoder
import java.util.*

object Pocket {

    private val url: String = "https://getpocket.com"

    internal var consumer_key: String = ""

    internal var access_token: String = ""

    fun init(consumer_key: String, access_token: String = "") {
        this.consumer_key = consumer_key
        this.access_token = access_token
    }

    internal val moshi: Moshi
        get() = Moshi.Builder()
                .add(AuthorsJsonAdapter())
                .add(ImagesJsonAdapter())
                .add(VideosJsonAdapter())
                .add(SendResultItemJsonAdapter())
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

    object Actions {

        class Builder() {

            private val actions: ArrayList<Any> = arrayListOf()

            fun add (params: ActionAddParams) : Builder {
                params.action = "add"
                params.time = Date().time
                actions.add(params)
                return this
            }

            fun tag_rename (params: ActionTagParams) : Builder {
                params.action = "tag_rename"
                params.time = Date().time
                actions.add(params)
                return this
            }

            fun archive (params: ActionBasicParams) : Builder {
                return basic("archive", params)
            }

            fun readd (params: ActionBasicParams) : Builder {
                return basic("readd", params)
            }

            fun favorite (params: ActionBasicParams) : Builder {
                return basic("favorite", params)
            }

            fun unfavorite (params: ActionBasicParams) : Builder {
                return basic("unfavorite", params)
            }

            fun delete (params: ActionBasicParams) : Builder {
                return basic("delete", params)
            }

            fun tags_clear (params: ActionBasicParams) : Builder {
                return basic("tags_clear", params)
            }

            fun tags_add (params: ActionTagsParams) : Builder {
                return tags("tags_add", params)
            }

            fun tags_remove (params: ActionTagsParams) : Builder {
                return tags("tags_remove", params)
            }

            fun tags_replace (params: ActionTagsParams) : Builder {
                return tags("tags_replace", params)
            }

            fun send () : SendResult? {
                if (actions.isEmpty())
                    return null
                val call = pocketInterface.send(getParams())
                val response = call.execute()
                val result = response.body()
                if (result == null)
                    handleError(response.headers())
                actions.clear()
                return result
            }

            private fun basic (action: String, params: ActionBasicParams) : Builder {
                params.action = action
                params.time = Date().time
                actions.add(params)
                return this
            }

            private fun tags (action: String, params: ActionTagsParams) : Builder {
                params.action = action
                params.time = Date().time
                actions.add(params)
                return this
            }

            private fun getParams () : Map<String, String> {
                val result = HashMap<String, String>()
                val json = actions.joinToString(separator = ",", prefix = "[", postfix = "]",
                        transform = { moshi.adapter(it.javaClass).toJson(it) })
                val encodedJson = URLEncoder.encode(json, Charsets.UTF_8.name())
                result.put("actions", encodedJson)
                result.put("access_token", URLEncoder.encode(access_token, Charsets.UTF_8.name()))
                result.put("consumer_key", URLEncoder.encode(Pocket.consumer_key, Charsets.UTF_8.name()))
                return result
            }
        }
    }

    fun add (params: AddParams) : AddResult? {
        val call = pocketInterface.add(params)
        val response = call.execute()
        val result = response.body()
        if (result == null)
            handleError(response.headers())
        return result
    }

    fun retrieve(params: RetrieveParams) : RetrieveResult {
        val call = pocketInterface.retrieve(params)
        val response = call.execute()
        val result = response.body()
        if (result == null)
            handleError(response.headers())
        return result?: RetrieveResult()
    }

    private fun handleError(headers: Headers?) {
        if (headers == null)
            return
        val errorCode = headers.get("X-Error-Code")
        val errorMessage = headers.get("X-Error")
        if (errorCode != null && errorMessage != null)
            throw RuntimeException("[$errorCode] $errorMessage")
    }
}
