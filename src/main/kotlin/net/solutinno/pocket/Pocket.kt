package net.solutinno.pocket

import com.squareup.moshi.Moshi
import net.solutinno.pocket.adapters.*
import net.solutinno.pocket.interfaces.PocketInterface
import net.solutinno.pocket.model.*
import retrofit.MoshiConverterFactory
import retrofit.Retrofit
import java.net.URLEncoder
import java.util.*

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

    object Actions {

        class Builder(private val access_token: String) {

            private val actions: ArrayList<ActionParams> = arrayListOf()

            fun send () : SendResult? {
                if (actions.isEmpty())
                    return null
                //val call = pocketInterface.send(getParams())
                val params = actions.toTypedArray()
                val json = Moshi.Builder().build().adapter(params.javaClass).toJson(params)
                val call = pocketInterface.send(
                        URLEncoder.encode(json, Charsets.UTF_8.name()), access_token, consumer_key)
                val response = call.execute()
                val result = response.body()
                actions.clear()
                return result
            }

            fun add (item_id: String? = null, init: ActionParams.Add.() -> Unit) : Builder {
                val param = ActionParams.Add("add", item_id)
                param.init()
                actions.add(param)
                return this
            }

            fun tag_rename (item_id: String? = null, init: ActionParams.Tag.() -> Unit) : Builder {
                val param = ActionParams.Tag("tag_rename", item_id)
                param.init()
                actions.add(param)
                return this
            }

            fun archive (item_id: String? = null, init: ActionParams.() -> Unit) : Builder {
                return basic("archive", item_id, init)
            }

            fun readd (item_id: String? = null, init: ActionParams.() -> Unit) : Builder {
                return basic("readd", item_id, init)
            }

            fun favorite (item_id: String? = null, init: ActionParams.() -> Unit) : Builder {
                return basic("favorite", item_id, init)
            }

            fun unfavorite (item_id: String? = null, init: ActionParams.() -> Unit) : Builder {
                return basic("unfavorite", item_id, init)
            }

            fun delete (item_id: String? = null, init: ActionParams.() -> Unit) : Builder {
                return basic("delete", item_id, init)
            }

            fun tags_clear (item_id: String? = null, init: ActionParams.() -> Unit) : Builder {
                return basic("tags_clear", item_id, init)
            }

            fun tags_add (item_id: String? = null, init: ActionParams.Tags.() -> Unit) : Builder {
                return tags("tags_add", item_id, init)
            }

            fun tags_remove (item_id: String? = null, init: ActionParams.Tags.() -> Unit) : Builder {
                return tags("tags_remove", item_id, init)
            }

            fun tags_replace (item_id: String? = null, init: ActionParams.Tags.() -> Unit) : Builder {
                return tags("tags_replace", item_id, init)
            }

            private fun basic (action: String, item_id: String? = null, init: ActionParams.() -> Unit) : Builder {
                val param = ActionParams(action, item_id)
                param.init()
                actions.add(param)
                return this
            }

            private fun tags (action: String, item_id: String? = null, init: ActionParams.Tags.() -> Unit) : Builder {
                val param = ActionParams.Tags(action, item_id)
                param.init()
                actions.add(param)
                return this
            }

            private fun getParams () : Map<String, String> {
                val params = actions.toTypedArray()
                val result = HashMap<String, String>()
                val json = Moshi.Builder().build().adapter(params.javaClass).toJson(params)
                result.put("actions", URLEncoder.encode(json, Charsets.UTF_8.name()))
                result.put("access_token", URLEncoder.encode(access_token, Charsets.UTF_8.name()))
                result.put("consumer_key", URLEncoder.encode(Pocket.consumer_key, Charsets.UTF_8.name()))
                return result
            }
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
