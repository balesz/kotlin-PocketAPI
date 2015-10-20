package net.solutinno.pocket.model

data class AddResult (
        val item: AddItem? = null,
        val status: String = "",
        val error: Int = 0
)
