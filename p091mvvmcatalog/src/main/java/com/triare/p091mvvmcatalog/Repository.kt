package com.triare.p091mvvmcatalog

class Repository {

    fun findCountry(id: Int) : Country? {
        val country = countries.find {
            it.id == id
        }
        return country
    }


    private val andorra = Country(R.id.andorra,
        R.string.andorra,
        R.drawable.ic_ad,
        R.string.andorra_description)

    private val portugal = Country(R.id.portugal,
        R.string.portugal,
        R.drawable.ic_pt,
        R.string.portugal_description)
    private val spain = Country(R.id.spain,
        R.string.spain,
        R.drawable.ic_ea,
        R.string.spain_description)
    private val poland = Country(R.id.poland,
        R.string.poland,
        R.drawable.ic_pl,
        R.string.poland_description)
    private val slovakia = Country(R.id.slovakia,
        R.string.slovakia,
        R.drawable.ic_sk,
        R.string.slovakia_description)
    private val slovenia = Country(R.id.slovenia,
        R.string.slovenia,
        R.drawable.ic_si,
        R.string.slovenia_description)
    private val ukraine = Country(R.id.ukraine,
        R.string.ukraine,
        R.drawable.ic_ua,
        R.string.ukraine_description)
    private val estonia = Country(R.id.estonia,
        R.string.estonia,
        R.drawable.ic_ee,
        R.string.estonia_description)
    private val latvia = Country(R.id.latvia,
        R.string.latvia,
        R.drawable.ic_lv,
        R.string.latvia_description)

    private val turkey = Country(R.id.turkey,
        R.string.turkey,
        R.drawable.ic_tr,
        R.string.turkey_description)
    private val georgia = Country(R.id.georgia,
        R.string.georgia,
        R.drawable.ic_ge,
        R.string.georgia_description)
    private val qatar = Country(R.id.qatar,
        R.string.qatar,
        R.drawable.ic_qa,
        R.string.qatar_description)
    private val kazakhstan = Country(R.id.kazakhstan,
        R.string.kazakhstan,
        R.drawable.ic_kz,
        R.string.kazakhstan_description)
    private val kyrgyzstan = Country(R.id.kyrgyzstan,
        R.string.kyrgyzstan,
        R.drawable.ic_kg,
        R.string.kyrgyzstan_description)
    private val tajikistan = Country(R.id.tajikistan,
        R.string.tajikistan,
        R.drawable.ic_tj,
        R.string.tajikistan_description)
    private val japan = Country(R.id.japan,
        R.string.japan,
        R.drawable.ic_jp,
        R.string.japan_description)
    private val southKorea = Country(R.id.south_korea,
        R.string.south_korea,
        R.drawable.ic_kr,
        R.string.s_korea_description)
    private val hongKong = Country(R.id.hong_kong,
        R.string.hong_kong,
        R.drawable.ic_hk,
        R.string.hong_kong_description)

    private val countries = listOf<Country>(andorra, portugal, spain,
        poland, slovakia, slovenia,
        ukraine, estonia, latvia,
        turkey, georgia, qatar,
        kazakhstan, kyrgyzstan, tajikistan,
        japan, southKorea, hongKong)


}

data class Country(val id: Int,
                   val title: Int,
                   val flag: Int,
                   val description: Int)