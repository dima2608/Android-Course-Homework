package com.triare.p151notepadapp.ui.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.triare.p151notepadapp.data.repository.NotepadRepository
import com.triare.p151notepadapp.ui.dvo.ContentDvo

class ContentViewModel: ViewModel() {

    private val notepadRepository = NotepadRepository()

    private var _contentListLiveData = MutableLiveData<List<ContentDvo>>()
    var contentListLiveData: LiveData<List<ContentDvo>> = _contentListLiveData

   init {
       getContentDvo()
   }

    private fun getContentDvo() {
        notepadRepository.getContentDvo {
            _contentListLiveData.value = it
        }
    }
}