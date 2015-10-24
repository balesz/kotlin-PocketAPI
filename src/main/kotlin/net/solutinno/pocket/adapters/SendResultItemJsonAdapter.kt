package net.solutinno.pocket.adapters

import com.squareup.moshi.*
import net.solutinno.pocket.Pocket
import net.solutinno.pocket.model.AddItem
import net.solutinno.pocket.model.SendResultItem

class SendResultItemJsonAdapter {
    @FromJson
    fun fromJson(reader: JsonReader) : SendResultItem? {
        if (reader.peek() == JsonReader.Token.BEGIN_OBJECT) {
            val adapter = Pocket.moshi.adapter(AddItem::class.java)
            return SendResultItem(addResult = adapter.fromJson(reader))
        } else if (reader.peek() == JsonReader.Token.BOOLEAN) {
            return SendResultItem(basicResult = reader.nextBoolean())
        }
        return null
    }
    @ToJson
    fun toJson (writer: JsonWriter, value: SendResultItem) {
        writer.toString()
        value.toString()
    }
}
