package net.solutinno.pocket.adapters

import net.solutinno.pocket.model.Video
import kotlin.reflect.KClass

class VideosJsonAdapter : ArrayMapJsonAdapter<Video> {
    override val type: KClass<Video> get() = Video::class
}
