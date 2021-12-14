package com.triare.p091mvvmcatalog

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.triare.p091mvvmcatalog.storage.Country

class MainViewModel : ViewModel() {

    private val repository = Repository()
    val countryLive = MutableLiveData<Country>()

    fun getCountry(id: Int) {
        countryLive.value = repository.findCountry(id)
    }
}