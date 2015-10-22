package net.solutinno.pocket.model

data class SendResult (
        val action_results: Array<SendResult.Item>? = null,
        val status: Int? = null)
{
    data class Item (
            val addResult: AddItem? = null,
            val basicResult: Boolean? = null)
}
