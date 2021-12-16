package com.triare.p091mvvmcatalog

import com.triare.p091mvvmcatalog.storage.*

class CountryRepository {

    fun findCountry(id: Int): CountryDvo? {
        val countries = getCountriesList()
        return countries.find {
            it.id == id
        }
    }

}
