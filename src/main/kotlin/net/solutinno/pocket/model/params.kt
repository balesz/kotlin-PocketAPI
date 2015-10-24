package net.solutinno.pocket.model

import net.solutinno.pocket.Pocket

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
        internal val consumer_key: String = Pocket.consumer_key,
        internal val access_token: String = Pocket.access_token,
        val url: String = "",
        val title: String = "",
        val tags: String = "",
        val tweet_id: String = "")

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
        internal val consumer_key: String = Pocket.consumer_key,
        internal val access_token: String = Pocket.access_token,
        val state: String? = null,
        val favorite: Int? = null,
        val tag: String? = null,
        val contentType: String? = null,
        val sort: String? = null,
        val detailType: String? = null,
        val search: String? = null,
        val domain: String? = null,
        val since: Long? = null,
        val count: Long? = null,
        val offset: Long? = null)
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

/**
 * @property action The action name.
 * @property item_id The id of the item to perform the action on.
 * @property time (optional) The time the action occurred.
 */
data class ActionBasicParams (
        internal var action: String = "",
        internal var time: Long? = null,
        val item_id: String)

/**
 * @property action The action name.
 * @property item_id The id of the item to perform the action on.
 * @property time (optional) The time the action occurred.
 * @property ref_id	(optional) A Twitter status id; this is used to show tweet attribution.
 * @property tags (optional) A comma-delimited list of one or more tags.
 * @property title (optional) The title of the item.
 * @property url (optional) The url of the item; provide this only if you do not have an item_id.
 */
data class ActionAddParams (
        internal var action: String = "",
        internal var time: Long? = null,
        val item_id: String? = null,
        val ref_id: Int? = null,
        val tags: String? = null,
        val title: String? = null,
        val url: String? = null)

/**
 * @property action The action name.
 * @property item_id The id of the item to perform the action on.
 * @property time (optional) The time the action occurred.
 * @property tags A comma-delimited list of one or more tags.
 */
data class ActionTagsParams (
        internal var action: String = "",
        internal var time: Long? = null,
        val item_id: String,
        val tags: String)

/**
 * @property action The action name.
 * @property item_id The id of the item to perform the action on.
 * @property time (optional) The time the action occurred.
 * @property old_tag The tag name that will be replaced.
 * @property new_tag The new tag name that will be added.
 */
data class ActionTagParams (
        internal var action: String = "",
        internal var time: Long? = null,
        val item_id: String? = null,
        val old_tag: String? = null,
        val new_tag: String? = null)
