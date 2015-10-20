package net.solutinno.pocket.interfaces

import net.solutinno.pocket.model.AuthorizeParams
import net.solutinno.pocket.model.AuthorizeResult
import net.solutinno.pocket.model.RequestParams
import net.solutinno.pocket.model.RequestResult
import retrofit.Call
import retrofit.http.*

internal interface AuthenticationInterface {

    /*Model.AuthenticationError.MISSING_CONSUMER_KEY
    Model.AuthenticationError.MISSING_REDIRECT_URI
    Model.AuthenticationError.INVALID_CONSUMER_KEY
    Model.AuthenticationError.POCKET_SERVER_ISSUE*/
    @Headers("Content-Type: application/json; charset=UTF-8", "X-Accept: application/json")
    @POST("/v3/oauth/request")
    fun request (@Body params: RequestParams) : Call<RequestResult>

    /*Model.AuthenticationError.MISSING_CONSUMER_KEY
    Model.AuthenticationError.INVALID_CONSUMER_KEY
    Model.AuthenticationError.INVALID_REDIRECT_URI
    Model.AuthenticationError.MISSING_CODE
    Model.AuthenticationError.CODE_NOT_FOUND
    Model.AuthenticationError.USER_REJECTED_CODE
    Model.AuthenticationError.ALREADY_USED_CODE
    Model.AuthenticationError.POCKET_SERVER_ISSUE*/
    @Headers("Content-Type: application/json; charset=UTF-8", "X-Accept: application/json")
    @POST("/v3/oauth/authorize")
    fun authorize (@Body params: AuthorizeParams) : Call<AuthorizeResult>
}
