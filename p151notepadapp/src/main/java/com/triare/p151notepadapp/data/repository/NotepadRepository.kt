package com.triare.p151notepadapp.data.repository


import androidx.lifecycle.MutableLiveData
import com.triare.p151notepadapp.data.db.AppDataBase
import com.triare.p151notepadapp.data.db.model.ContentDbo
import com.triare.p151notepadapp.data.db.model.NoteDbo
import com.triare.p151notepadapp.data.maper.ContentMapper
import com.triare.p151notepadapp.data.maper.NoteMapper
import com.triare.p151notepadapp.ui.dvo.ContentDvo
import com.triare.p151notepadapp.ui.dvo.NoteDvo
import com.triare.p151notepadapp.utisl.TimeUtils

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

    fun getLatContentId(): MutableLiveData<Long> {
        lastContentId.value = contentDao.getLastContentId()
        return lastContentId
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
}