package com.example.examenpoo2.ui.Model

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * Convertidores para permitir que Room guarde tipos de datos complejos como Maps.
 */
class Converters {
    @TypeConverter
    fun fromStringMap(value: Map<String, Int>): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toStringMap(value: String): Map<String, Int> {
        return Json.decodeFromString(value)
    }
}
