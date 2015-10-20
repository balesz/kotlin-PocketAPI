package net.solutinno.pocket.model

data class RequestResult (
        val code: String,
        val state: String,
        val error: Int = 0
)
