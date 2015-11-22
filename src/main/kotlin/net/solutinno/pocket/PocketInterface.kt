package net.solutinno.pocket

import net.solutinno.pocket.model.*
import retrofit.Call
import retrofit.http.*
import rx.Observable

internal interface PocketInterface {
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
    fun send (@QueryMap(encoded = true) params: Map<String, String>) : Call<SendResult>

    //region Reactive extensions

    @Headers("Content-Type: application/json; charset=UTF-8", "X-Accept: application/json")
    @POST("/v3/oauth/request")
    fun rxRequest (@Body params: RequestParams) : Observable<RequestResult>

    @Headers("Content-Type: application/json; charset=UTF-8", "X-Accept: application/json")
    @POST("/v3/oauth/authorize")
    fun rxAuthorize (@Body params: AuthorizeParams) : Observable<AuthorizeResult>

    //endregion
}
