package com.triare.p151notes.data.repository


import androidx.lifecycle.MutableLiveData
import com.triare.p151notes.data.db.AppDataBase
import com.triare.p151notes.data.db.model.ContentDbo
import com.triare.p151notes.data.db.model.NoteDbo
import com.triare.p151notes.data.maper.ContentMapper
import com.triare.p151notes.data.maper.NoteMapper
import com.triare.p151notes.ui.dvo.ContentDvo
import com.triare.p151notes.ui.dvo.NoteDvo

class NotepadRepository {

    private val db = AppDataBase.getInstance()
    private val contentDao = db.contentDao()
    private val noteDao = db.noteDao()

    private val lastContentId = MutableLiveData<Long>()

    fun getContentDvo(result: (List<ContentDvo>) -> Unit) {
        val contentListDbo = contentDao.getContentList()
        result(ContentMapper(contentListDbo).map())
    }


    fun getNoteDvo(ownerContentId: Long, result: (List<NoteDvo>) -> Unit) {
        val noteListDbo = noteDao.getNoteList(ownerContentId)
        result(NoteMapper(noteListDbo).map())
    }

    fun getFractions(ownerContentId: Long): String {
        val finishedTasksCount = noteDao.getFinishedTasksCount(ownerContentId)
        val allTasksCount = noteDao.getTasksCount(ownerContentId)

        return if (allTasksCount >= 1)
            "${finishedTasksCount}/${allTasksCount}"
        else
            ""
    }

    fun setTile(contentId: Long, title: String) {
        contentDao.updateTitle(contentId, title)
    }

    fun deleteContent(contentId: Long) {
        contentDao.deleteContent(contentId)
    }

    fun insertContent() {
        contentDao.insert(
            ContentDbo()
        )
    }

    fun getLatContentId(result: (Long) -> Unit){
        result(contentDao.getLastContentId())
        //lastContentId.value = contentDao.getLastContentId()
    }

    fun insertNote(ownerContentId: Long) {
        noteDao.insert(
            NoteDbo(
                ownerContentId = ownerContentId
            )
        )
    }

    fun changeCompleteStatement(noteId: Long, completed: Boolean) {
        noteDao.updateCompleted(noteId, completed)
    }

    fun deleteNote(noteId: Long) {
        noteDao.deleteNote(noteId)
    }

    fun updateText(noteId: Long, text: String) {
        noteDao.updateText(noteId, text)
    }

    fun getTitle(contentId: Long): String {
        return contentDao.getTitle(contentId)
    }
}