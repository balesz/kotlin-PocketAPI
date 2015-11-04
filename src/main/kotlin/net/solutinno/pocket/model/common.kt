package net.solutinno.pocket.model

data class Author (
        val author_id: String?,
        val name: String?,
        val url: String?)

data class Image (
        val item_id: String?,
        val image_id: String?,
        val src: String?,
        val width: String?,
        val height: String?,
        val credit: String?,
        val caption: String?)

data class Video (
        val item_id: String?,
        val video_id: String?,
        val src: String?,
        val width: String?,
        val height: String?,
        val type: String?,
        val vid: String?,
        val length: String?)

data class Tag (
        val item_id: String?,
        val tag: String?
)
