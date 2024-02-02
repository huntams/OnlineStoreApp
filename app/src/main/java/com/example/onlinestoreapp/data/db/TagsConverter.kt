package com.example.onlinestoreapp.data.db

import androidx.room.TypeConverter
import java.util.stream.Collectors


class TagsConverter {
    @TypeConverter
    fun fromTags(tags: List<String>): String {
        return tags.stream().collect(Collectors.joining(","))
    }

    @TypeConverter
    fun toTags(data: String): List<String> {
        return data.split(",").dropLastWhile { it.isEmpty() }
    }
}