package com.triare.p091mvvmcatalog

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel: ViewModel() {

    private val repository = Repository()
    val countryLive = MutableLiveData<Country>()

    fun getCountry(id: Int) {
        countryLive.value = repository.findCountry(id)
    }
}