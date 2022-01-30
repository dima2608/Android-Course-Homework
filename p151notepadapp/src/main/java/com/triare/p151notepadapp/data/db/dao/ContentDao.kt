package com.triare.p151notepadapp.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.triare.p151notepadapp.data.db.model.ContentDbo

@Dao
interface ContentDao {

    // Read
    @Query("SELECT * FROM contents ORDER BY contentId DESC")
    fun getContentList(): List<ContentDbo>

    @Query("SELECT * FROM contents WHERE contentId = (SELECT MAX(contentId) FROM contents)")
    fun getLastContentId(): Long

    // Create
    @Insert
    fun insert(contentDbo: ContentDbo)

    //Update
    @Query("UPDATE contents SET title = :title WHERE contentId = :contentId")
    fun updateTitle(contentId: Long, title: String)

    @Query("UPDATE contents SET lastUpdateMillis = :millis WHERE contentId = :contentId")
    fun updateMillis(contentId: Long, millis: Long)

    //Delete
    @Query("DELETE FROM contents WHERE contentId = :contentId")
    fun deleteContent(contentId: Long)
}