package com.triare.p151notes.data.db.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.triare.p151notes.data.db.model.ContentDbo

@Entity(
    tableName = "notes", foreignKeys = [ForeignKey(
        entity = ContentDbo::class,
        parentColumns = arrayOf("contentId"),
        childColumns = arrayOf("ownerContentId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class NoteDbo(
    @PrimaryKey(autoGenerate = true)
    val noteId: Long = 0,
    val ownerContentId: Long,
    val text: String = "",
    val completed: Boolean = false
)
