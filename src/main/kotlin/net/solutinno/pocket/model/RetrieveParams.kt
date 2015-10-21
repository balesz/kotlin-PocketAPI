package net.solutinno.pocket.model

/**
 * @property consumer_key Your application's Consumer Key
 * @property access_token The user's Pocket access token
 * @property state See below for valid values
 * @property favorite See below for valid values
 * @property tag See below for valid values
 * @property contentType See below for valid values
 * @property sort See below for valid values
 * @property detailType See below for valid values
 * @property search Only return items whose title or url contain the search string
 * @property domain Only return items from a particular domain
 * @property since Only return items modified since the given since unix timestamp
 * @property count Only return count number of items
 * @property offset Used only with count; start returning from offset position of results
 */
data class RetrieveParams (
        val consumer_key: String,
        val access_token: String,
        var state: String? = null,
        var favorite: Int? = null,
        var tag: String? = null,
        var contentType: String? = null,
        var sort: String? = null,
        var detailType: String? = null,
        var search: String? = null,
        var domain: String? = null,
        var since: Long? = null,
        var count: Long? = null,
        var offset: Long? = null)
{
    companion object {
        val STATE_ALL = "all"
        val STATE_UNREAD = "unread"
        val STATE_ARCHIVE = "archive"
        val FAVOURITE_ONLY_UN_FAVOURITED = "0"
        val FAVOURITE_ONLY_FAVOURITED = "1"
        val TAG_UNTAGGED = "__untagged__"
        val SORT_NEWEST = "newest"
        val SORT_OLDEST = "oldest"
        val SORT_TITLE = "title"
        val SORT_SITE = "site"
        val DETAIL_TYPE_SIMPLE = "simple"
        val DETAIL_TYPE_COMPLETE = "complete"
    }
}
