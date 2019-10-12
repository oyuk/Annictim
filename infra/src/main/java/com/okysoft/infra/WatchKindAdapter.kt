package com.okysoft.infra

import com.google.gson.*
import com.okysoft.data.WatchKind
import java.lang.reflect.Type

class WatchKindAdapter {
    companion object : JsonSerializer<WatchKind?>, JsonDeserializer<WatchKind?> {
        override fun serialize(src: WatchKind?, typeOfSrc: Type?, context: JsonSerializationContext?): JsonElement {
            val jsonObject = JsonObject()
            jsonObject.add("watch_kind", JsonPrimitive(src.toString()))
            return jsonObject
        }

        override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): WatchKind {
            return WatchKind.fromString(json?.asString ?: "")
        }
    }
}