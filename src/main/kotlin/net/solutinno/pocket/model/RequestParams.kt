package net.solutinno.pocket.model

data class RequestParams (
        val consumer_key: String,
        val redirect_uri: String,
        val state: String = ""
)
