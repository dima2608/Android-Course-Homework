package com.triare.p102quakealertapp.ui.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.triare.p102quakealertapp.data.repository.QuakeRepository
import com.triare.p102quakealertapp.ui.dvo.MapsDvo

class MapsViewModel : ViewModel() {

    private val quakeRepository = QuakeRepository()

    private var _mapsQuakeResultLiveData = MutableLiveData<List<MapsDvo>>()
    val mapsQuakeResultLiveData: LiveData<List<MapsDvo>> = _mapsQuakeResultLiveData

    init {
        getMapsQuake()
    }

    private fun getMapsQuake() {
        quakeRepository.getMapsQuake {
            _mapsQuakeResultLiveData.value = it
        }
    }

}