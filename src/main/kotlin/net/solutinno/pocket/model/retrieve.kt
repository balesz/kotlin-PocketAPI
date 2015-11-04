package net.solutinno.pocket.model

import net.solutinno.pocket.Pocket
import java.util.*

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
    /**
     * @property STATE_UNREAD only return unread items (default)
     * @property STATE_ARCHIVE only return archived items
     * @property STATE_ALL return both unread and archived items
     * @property FAVORITE_ONLY_UN_FAVORITED only return un-favorited items
     * @property FAVORITE_ONLY_FAVORITED only return favorited items
     * @property TAG_UNTAGGED only return untagged items
     * @property CONTENT_TYPE_ARTICLE only return articles
     * @property CONTENT_TYPE_IMAGE only return images
     * @property CONTENT_TYPE_VIDEO only return videos or articles with embedded videos
     * @property SORT_NEWEST return items in order of newest to oldest
     * @property SORT_OLDEST return items in order of oldest to newest
     * @property SORT_TITLE return items in order of title alphabetically
     * @property SORT_SITE return items in order of url alphabetically
     * @property DETAIL_TYPE_SIMPLE only return the titles and urls of each item
     * @property DETAIL_TYPE_COMPLETE return all data about each item, including tags, images, authors, videos and more
     */
    companion object {
        val STATE_ALL = "all"
        val STATE_UNREAD = "unread"
        val STATE_ARCHIVE = "archive"
        val FAVORITE_ONLY_UN_FAVORITED = "0"
        val FAVORITE_ONLY_FAVORITED = "1"
        val TAG_UNTAGGED = "__untagged__"
        val CONTENT_TYPE_ARTICLE = "article"
        val CONTENT_TYPE_IMAGE = "image"
        val CONTENT_TYPE_VIDEO = "video"
        val SORT_NEWEST = "newest"
        val SORT_OLDEST = "oldest"
        val SORT_TITLE = "title"
        val SORT_SITE = "site"
        val DETAIL_TYPE_SIMPLE = "simple"
        val DETAIL_TYPE_COMPLETE = "complete"
    }
}

/**
 * @property status
 * @property complete
 * @property list
 * @property error
 * @property search_meta
 * @property since
 */
data class RetrieveResult (
        val status: Int? = null,
        val complete: Int? = null,
        val list: Map<String, RetrieveItem>? = null,
        val error: String? = null,
        val search_meta: SearchMeta? = null,
        val since: Long = 0)

/**
 * @property item_id
 * @property resolved_id
 * @property given_url
 * @property given_title
 * @property favorite
 * @property status
 * @property time_added
 * @property time_updated
 * @property time_read
 * @property time_favorited
 * @property sort_id
 * @property resolved_title
 * @property resolved_url
 * @property excerpt
 * @property is_article
 * @property is_index
 * @property has_video
 * @property has_image
 * @property word_count
 * @property tags
 * @property authors
 * @property image
 * @property images
 * @property videos
 */
data class RetrieveItem (
        val item_id: String?,
        val resolved_id: String?,
        val given_url: String?,
        val given_title: String?,
        val favorite: String?,
        val status: String?,
        val time_added: Long?,
        val time_updated: Long?,
        val time_read: Long?,
        val time_favorited: Long?,
        val sort_id: Int?,
        val resolved_title: String?,
        val resolved_url: String?,
        val excerpt: String?,
        val is_article: Int?,
        val is_index: Int?,
        val has_video: Int?,
        val has_image: Int?,
        val word_count: Long?,
        val tags: HashMap<String, Tag>?,
        val authors: HashMap<String, Author>?,
        val image: Image?,
        val images: HashMap<String, Image>?,
        val videos: HashMap<String, Video>?)

/**
 * @property search_type
 */
data class SearchMeta (
        val search_type: String?)
