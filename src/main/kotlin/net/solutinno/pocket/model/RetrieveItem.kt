package net.solutinno.pocket.model

import java.util.*

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
        val tags: String?,
        val authors: HashMap<String, Author>?,
        val image: Image?,
        val images: HashMap<String, Image>?,
        val videos: HashMap<String, Video>?)
