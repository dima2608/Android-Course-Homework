package com.triare.p102quakealertapp.ui.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.triare.p102quakealertapp.data.repository.QuakeRepository
import com.triare.p102quakealertapp.ui.dvo.FeatureQuakeDvo

class QuakeViewModel : ViewModel() {

    private val quakeRepository = QuakeRepository()

    private var _quakeResultLiveData = MutableLiveData<List<FeatureQuakeDvo>>()
    val quakeResultLiveData: LiveData<List<FeatureQuakeDvo>> = _quakeResultLiveData

    init {
        getFeatureQuake()
    }

    private fun getFeatureQuake() {
        quakeRepository.getQuake {
            _quakeResultLiveData.value = it
        }
    }

}