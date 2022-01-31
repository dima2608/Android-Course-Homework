package com.triare.p151notepadapp.ui.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.triare.p151notepadapp.data.repository.NotepadRepository
import com.triare.p151notepadapp.ui.dvo.ContentDvo
import com.triare.p151notepadapp.ui.dvo.NoteDvo

class NoteViewModel : ViewModel() {

    private val notepadRepository = NotepadRepository()

    private var _noteListLiveData = MutableLiveData<List<NoteDvo>>()
    var noteListLiveData: LiveData<List<NoteDvo>> = _noteListLiveData

    fun setTitle(contentId: Long, title: String) {
        notepadRepository.setTile(contentId, title)
    }

    fun deleteContent(contentId: Long){
        notepadRepository.deleteContent(contentId)
    }

    fun setIsCompletedNote(noteId: Long, completed: Boolean) {
        notepadRepository.changeCompleteStatement(noteId, completed)
    }

    fun getNoteDvo(ownerContentId: Long): MutableLiveData<List<NoteDvo>> {
        notepadRepository.getNoteDvo(ownerContentId) {
            _noteListLiveData.value = it

        }
        return _noteListLiveData
    }
    fun deleteNote(noteId: Long) {
        notepadRepository.deleteNote(noteId)
    }

    fun setTextNote(noteId: Long, text: String) {
        notepadRepository.updateText(noteId, text)
    }

    fun createNote(contentId: Long) {
        notepadRepository.insertNote(contentId)
    }
}