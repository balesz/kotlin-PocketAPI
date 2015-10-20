package net.solutinno.pocket.adapters

import com.squareup.moshi.*
import java.util.*
import kotlin.reflect.KClass

interface ArrayMapJsonAdapter<T : Any> {

    val type: KClass<T>

    @FromJson
    fun fromJson(reader: JsonReader?): HashMap<String, T>? {
        if (reader == null)
            return null
        if (reader.peek() == JsonReader.Token.BEGIN_ARRAY) {
            reader.beginArray()
            reader.endArray()
            return null
        }
        else if (reader.peek() != JsonReader.Token.BEGIN_OBJECT) {
            return null
        }
        val result = HashMap<String, T>()
        val valueAdapter = Moshi.Builder().build().adapter(type.java)
        reader.beginObject()
        while (reader.hasNext()) {
            val key = reader.nextName()
            val value = valueAdapter.fromJson(reader)
            result.put(key, value)
        }
        reader.endObject()
        return result
    }

    @ToJson
    fun toJson (writer: JsonWriter?, authors: HashMap<String, T>) {
        if (writer == null)
            return
        val valueAdapter = Moshi.Builder().build().adapter(type.java)
        for (key in authors.keySet()) {
            writer.name(key)
            valueAdapter.toJson(writer, authors.get(key))
        }
    }
}
