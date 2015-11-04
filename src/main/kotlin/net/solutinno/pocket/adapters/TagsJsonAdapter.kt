package net.solutinno.pocket.adapters

import net.solutinno.pocket.model.Tag
import kotlin.reflect.KClass

class TagsJsonAdapter : ArrayMapJsonAdapter<Tag> {
    override val type: KClass<Tag> get() = Tag::class
}
