package net.solutinno.pocket.model

data class AuthorizeParams (
        val consumer_key: String,
        val code: String)

data class AuthorizeResult(
        val access_token: String,
        val username: String,
        val error: Int = 0)


data class RequestParams (
        val consumer_key: String,
        val redirect_uri: String,
        val state: String = "")

data class RequestResult (
        val code: String,
        val state: String,
        val error: Int = 0)
