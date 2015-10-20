package net.solutinno.pocket.adapters

import net.solutinno.pocket.model.Author
import kotlin.reflect.KClass

class AuthorsJsonAdapter : ArrayMapJsonAdapter<Author> {
    override val type: KClass<Author> get() = Author::class
}
