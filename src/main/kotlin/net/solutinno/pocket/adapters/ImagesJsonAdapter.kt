package net.solutinno.pocket.adapters

import net.solutinno.pocket.model.Image
import kotlin.reflect.KClass

class ImagesJsonAdapter : ArrayMapJsonAdapter<Image> {
    override val type: KClass<Image> get() = Image::class
}
