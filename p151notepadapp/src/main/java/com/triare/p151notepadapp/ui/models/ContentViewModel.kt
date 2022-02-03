package com.triare.p151notepadapp.ui.models

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.triare.p151notepadapp.data.repository.NotepadRepository
import com.triare.p151notepadapp.ui.dvo.ContentDvo

class ContentViewModel: ViewModel() {

    private val notepadRepository = NotepadRepository()

    private var _contentListLiveData = MutableLiveData<List<ContentDvo>>()
    var contentListLiveData: LiveData<List<ContentDvo>> = _contentListLiveData

    private var _contentIdLiveData = MutableLiveData<Long>()
    var contentIdLiveData: LiveData<Long> = _contentIdLiveData

    fun getContentDvo() {
        notepadRepository.getContentDvo {
            _contentListLiveData.value = it
        }
    }

    fun getCreatedContentId() {
        notepadRepository.getLatContentId(){
            _contentIdLiveData.value = it
        }
        //_contentIdLiveData.value = notepadRepository.getLatContentId().value
    }

    fun createContent(){
        notepadRepository.insertContent()
    }

    fun createNote(contentId: Long) {
        notepadRepository.insertNote(contentId)
    }
}