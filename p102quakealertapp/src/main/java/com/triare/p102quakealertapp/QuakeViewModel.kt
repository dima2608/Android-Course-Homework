package com.triare.p102quakealertapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.triare.p102quakealertapp.model.QuakeDto

class QuakeViewModel : ViewModel() {

    private val repository = QuakeRepository()
    private var quakeResultLiveData = MutableLiveData<QuakeDto>()

    init {
        quakeResultLiveData = repository.getQuakeDto()
    }

    fun getQuakeResultLiveData(): LiveData<QuakeDto> {
        return quakeResultLiveData
    }
}