package com.triare.p151notes.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contents")
data class ContentDbo(
    @PrimaryKey(autoGenerate = true)
    val contentId: Long = 0,
    val title: String = "",
    val lastUpdateMillis: Long = System.currentTimeMillis()
)