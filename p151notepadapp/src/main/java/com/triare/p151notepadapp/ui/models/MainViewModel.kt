package com.triare.p151notepadapp.ui.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.triare.p151notepadapp.data.repository.NotepadRepository

class MainViewModel: ViewModel() {

    private val notepadRepository = NotepadRepository()

    private var _contentIdLiveData = MutableLiveData<Long>()
    var contentIdLiveData: LiveData<Long> = _contentIdLiveData

    fun createContent(){
        notepadRepository.insertContent()
    }

    fun getCreatedContentId() {
        //_contentIdLiveData.value = notepadRepository.getLatContentId().value
    }

    fun addNote(contentId: Long) {
        notepadRepository.insertNote(contentId)
    }
}