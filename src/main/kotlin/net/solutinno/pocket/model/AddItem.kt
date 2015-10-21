package net.solutinno.pocket.model

import java.util.*

/**
 * @property item_id A unique identifier for the added item
 * @property normal_url The original url for the added item
 * @property resolved_id A unique identifier for the resolved item
 * @property extended_item_id
 * @property resolved_url The resolved url for the added item. The easiest way to think about the resolved_url -
 * if you add a bit.ly link, the resolved_url will be the url of the page the bit.ly link points to
 * @property domain_id A unique identifier for the domain of the resolved_url
 * @property origin_domain_id A unique identifier for the domain of the normal_url
 * @property response_code The response code received by the Pocket parser when it tried to access the item
 * @property mime_type The MIME type returned by the item
 * @property content_length The content length of the item
 * @property encoding The encoding of the item
 * @property date_resolved The date the item was resolved
 * @property date_published The date the item was published (if the parser was able to find one)
 * @property title The title of the resolved_url
 * @property excerpt The excerpt of the resolved_url
 * @property word_count For an article, the number of words
 * @property innerdomain_redirect
 * @property login_required
 * @property has_image 0: no image; 1: has an image in the body of the article; 2: is an image
 * @property has_video 0: no video; 1: has a video in the body of the article; 2: is a video
 * @property is_index 0 or 1; If the parser thinks this item is an index page it will be set to 1
 * @property is_article 0 or 1; If the parser thinks this item is an article it will be set to 1
 * @property used_fallback
 * @property lang
 * @property authors Array of author data (if author(s) were found)
 * @property images Array of image data (if image(s) were found)
 * @property videos Array of video data (if video(s) were found)
 * @property resolved_normal_url
 * @property given_url
 */
data class AddItem (
        val item_id: String?,
        val normal_url: String?,
        val resolved_id: String?,
        val extended_item_id: String?,
        val resolved_url: String?,
        val domain_id: String?,
        val origin_domain_id: String?,
        val response_code: String?,
        val mime_type: String?,
        val content_length: String?,
        val encoding: String?,
        val date_resolved: String?,
        val date_published: String?,
        val title: String?,
        val excerpt: String?,
        val word_count: String?,
        val innerdomain_redirect: String?,
        val login_required: String?,
        val has_image: String?,
        val has_video: String?,
        val is_index: String?,
        val is_article: String?,
        val used_fallback: String?,
        val lang: String?,
        val authors: HashMap<String, Author>?,
        val images: HashMap<String, Image>?,
        val videos: HashMap<String, Video>?,
        val resolved_normal_url: String?,
        val given_url: String?)
