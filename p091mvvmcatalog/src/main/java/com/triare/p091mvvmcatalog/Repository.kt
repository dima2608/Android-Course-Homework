package com.triare.p091mvvmcatalog

import com.triare.p091mvvmcatalog.storage.*

class Repository {

    fun findCountry(id: Int): Country? {
        return countries.find {
            it.id == id
        }
    }

    private val countries = listOf(
        andorra, portugal, spain,
        poland, slovakia, slovenia,
        ukraine, estonia, latvia,
        turkey, georgia, qatar,
        kazakhstan, kyrgyzstan, tajikistan,
        japan, southKorea, hongKong
    )
}
