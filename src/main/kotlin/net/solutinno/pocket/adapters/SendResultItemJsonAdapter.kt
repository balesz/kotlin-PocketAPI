package net.solutinno.pocket.adapters

import com.squareup.moshi.*
import net.solutinno.pocket.Pocket
import net.solutinno.pocket.model.AddItem
import net.solutinno.pocket.model.SendResult

class SendResultItemJsonAdapter {
    @FromJson
    fun fromJson(reader: JsonReader) : SendResult.Item? {
        if (reader.peek() == JsonReader.Token.BEGIN_OBJECT) {
            val adapter = Pocket.moshi.adapter(AddItem::class.java)
            return SendResult.Item(addResult = adapter.fromJson(reader))
        } else if (reader.peek() == JsonReader.Token.BOOLEAN) {
            return SendResult.Item(basicResult = reader.nextBoolean())
        }
        return null
    }
    @ToJson
    fun toJson (writer: JsonWriter, value: SendResult.Item) {
        writer.toString()
        value.toString()
    }
}
