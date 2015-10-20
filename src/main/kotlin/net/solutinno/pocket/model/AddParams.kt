package net.solutinno.pocket.model

/**
 * Adding a Single Item
 * Parameters:
 * @property consumer_key Your application's Consumer Key
 * @property access_token The user's Pocket access token
 * @property url The URL of the item you want to save
 * @property title (optional) This can be included for cases where an item does not have a title, which is typical for image
 * or PDF URLs. If Pocket detects a title from the content of the page, this parameter will be ignored.
 * @property tags (optional) A comma-separated list of tags to apply to the item
 * @property tweet_id (optional) If you are adding Pocket support to a Twitter client, please send along a reference to the
 * tweet status id. This allows Pocket to show the original tweet alongside the article.
 */
data class AddParams (
        val consumer_key: String,
        val access_token: String,
        val url: String,
        val title: String = "",
        val tags: String = "",
        val tweet_id: String = ""
)
