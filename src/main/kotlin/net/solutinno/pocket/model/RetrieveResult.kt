package net.solutinno.pocket.model

data class RetrieveResult (
        val status: Int? = null,
        val complete: Int? = null,
        val list: Map<String, RetrieveItem>? = null,
        val error: String? = null,
        val search_meta: SearchMeta? = null,
        val since: Long = 0)
