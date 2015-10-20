package net.solutinno.pocket.interfaces

import net.solutinno.pocket.model.AddParams
import net.solutinno.pocket.model.AddResult
import retrofit.Call
import retrofit.http.Body
import retrofit.http.Headers
import retrofit.http.POST

internal interface AddInterface {

    @Headers("Content-Type: application/json; charset=UTF-8", "X-Accept: application/json")
    @POST("/v3/add")
    fun add(@Body params: AddParams) : Call<AddResult>
}
