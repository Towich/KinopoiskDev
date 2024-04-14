package com.towich.kinopoiskDev.data.room.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "queries_table")
data class QueryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int?,
    val query: String?
)