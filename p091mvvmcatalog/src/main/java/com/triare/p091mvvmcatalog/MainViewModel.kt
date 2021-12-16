package com.triare.p091mvvmcatalog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.triare.p091mvvmcatalog.storage.CountryDvo

class MainViewModel : ViewModel() {

    private val repository = CountryRepository()
    private val _countryLiveData = MutableLiveData<CountryDvo>()
    val countryLiveData: LiveData<CountryDvo> = _countryLiveData

    fun getCountry(id: Int) {
        _countryLiveData.value = repository.findCountry(id)
    }
}