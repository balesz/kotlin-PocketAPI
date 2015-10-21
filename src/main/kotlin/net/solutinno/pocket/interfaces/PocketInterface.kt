package net.solutinno.pocket.interfaces

import net.solutinno.pocket.model.*
import retrofit.Call
import retrofit.http.*

interface PocketInterface {
    @Headers("Content-Type: application/json; charset=UTF-8", "X-Accept: application/json")
    @POST("/v3/oauth/request")
    fun request (@Body params: RequestParams) : Call<RequestResult>

    @Headers("Content-Type: application/json; charset=UTF-8", "X-Accept: application/json")
    @POST("/v3/oauth/authorize")
    fun authorize (@Body params: AuthorizeParams) : Call<AuthorizeResult>

    @Headers("Content-Type: application/json; charset=UTF-8", "X-Accept: application/json")
    @POST("/v3/add")
    fun add (@Body params: AddParams) : Call<AddResult>

    @Headers("Content-Type: application/json; charset=UTF-8", "X-Accept: application/json")
    @POST("/v3/get")
    fun retrieve (@Body params: RetrieveParams) : Call<RetrieveResult>

    @GET("/v3/send")
    fun send (@Query("actions") actions: String,
              @Query("access_token") access_token: String,
              @Query("consumer_key") consumer_key: String
    ) : Call<SendResult>
}