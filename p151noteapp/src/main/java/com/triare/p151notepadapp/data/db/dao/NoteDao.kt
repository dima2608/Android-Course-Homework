package com.triare.p151notepadapp.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.triare.p151notepadapp.data.db.model.NoteDbo

@Dao
interface NoteDao{

    // Read
    @Query("SELECT * FROM notes WHERE ownerContentId = :ownerContentId")
    fun getNoteList(ownerContentId: Long): List<NoteDbo>

    @Query("SELECT COUNT(*) FROM notes WHERE ownerContentId = :ownerContentId")
    fun getTasksCount(ownerContentId: Long):Int

    @Query("SELECT COUNT(*) FROM notes WHERE ownerContentId = :ownerContentId AND completed = 1")
    fun getFinishedTasksCount(ownerContentId: Long):Int

    // Create
    @Insert
    fun insert(noteDbo: NoteDbo)

    //Update
    @Query("UPDATE notes SET text = :text WHERE noteId = :noteId")
    fun updateText(noteId: Long, text: String)

    @Query("UPDATE notes SET completed = :completed WHERE noteId = :noteId")
    fun updateCompleted(noteId: Long, completed: Boolean)

    //Delete
    @Query("DELETE FROM notes WHERE noteId = :noteId")
    fun deleteNote(noteId: Long)
}