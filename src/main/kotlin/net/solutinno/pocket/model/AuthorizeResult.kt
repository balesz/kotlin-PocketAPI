package net.solutinno.pocket.model

data class AuthorizeResult(
        val access_token: String,
        val username: String,
        val error: Int = 0
)
