package com.triare.p102quakealertapp.ui.quake.fragments.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.triare.p102quakealertapp.data.repository.QuakeRepository
import com.triare.p102quakealertapp.data.api.model.QuakeDto
import com.triare.p102quakealertapp.ui.quake.dvo.FeatureQuakeDvo

class QuakeViewModel : ViewModel() {

    private val quakeRepository = QuakeRepository()

    private var _quakeResultLiveData = MutableLiveData<List<FeatureQuakeDvo>>()
    val quakeResultLiveData: LiveData<List<FeatureQuakeDvo>> = _quakeResultLiveData

    init {
        qetFeatureQuake()
    }

    private fun qetFeatureQuake() {
        quakeRepository.getQuake {
            _quakeResultLiveData.value = it
        }
    }

}