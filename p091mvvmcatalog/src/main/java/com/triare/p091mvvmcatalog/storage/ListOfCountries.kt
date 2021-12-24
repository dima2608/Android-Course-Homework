package com.triare.p091mvvmcatalog.storage

import com.triare.p091mvvmcatalog.R

val andorra = CountryDvo(
    R.id.andorra,
    R.string.andorra,
    R.drawable.ic_ad,
    R.string.andorra_description
)

val portugal = CountryDvo(
    R.id.portugal,
    R.string.portugal,
    R.drawable.ic_pt,
    R.string.portugal_description
)
val spain = CountryDvo(
    R.id.spain,
    R.string.spain,
    R.drawable.ic_ea,
    R.string.spain_description
)
val poland = CountryDvo(
    R.id.poland,
    R.string.poland,
    R.drawable.ic_pl,
    R.string.poland_description
)
val slovakia = CountryDvo(
    R.id.slovakia,
    R.string.slovakia,
    R.drawable.ic_sk,
    R.string.slovakia_description
)
val slovenia = CountryDvo(
    R.id.slovenia,
    R.string.slovenia,
    R.drawable.ic_si,
    R.string.slovenia_description
)
val ukraine = CountryDvo(
    R.id.ukraine,
    R.string.ukraine,
    R.drawable.ic_ua,
    R.string.ukraine_description
)
val estonia = CountryDvo(
    R.id.estonia,
    R.string.estonia,
    R.drawable.ic_ee,
    R.string.estonia_description
)
val latvia = CountryDvo(
    R.id.latvia,
    R.string.latvia,
    R.drawable.ic_lv,
    R.string.latvia_description
)

val turkey = CountryDvo(
    R.id.turkey,
    R.string.turkey,
    R.drawable.ic_tr,
    R.string.turkey_description
)
val georgia = CountryDvo(
    R.id.georgia,
    R.string.georgia,
    R.drawable.ic_ge,
    R.string.georgia_description
)
val qatar = CountryDvo(
    R.id.qatar,
    R.string.qatar,
    R.drawable.ic_qa,
    R.string.qatar_description
)
val kazakhstan = CountryDvo(
    R.id.kazakhstan,
    R.string.kazakhstan,
    R.drawable.ic_kz,
    R.string.kazakhstan_description
)
val kyrgyzstan = CountryDvo(
    R.id.kyrgyzstan,
    R.string.kyrgyzstan,
    R.drawable.ic_kg,
    R.string.kyrgyzstan_description
)
val tajikistan = CountryDvo(
    R.id.tajikistan,
    R.string.tajikistan,
    R.drawable.ic_tj,
    R.string.tajikistan_description
)
val japan = CountryDvo(
    R.id.japan,
    R.string.japan,
    R.drawable.ic_jp,
    R.string.japan_description
)
val southKorea = CountryDvo(
    R.id.south_korea,
    R.string.south_korea,
    R.drawable.ic_kr,
    R.string.s_korea_description
)
val hongKong = CountryDvo(
    R.id.hong_kong,
    R.string.hong_kong,
    R.drawable.ic_hk,
    R.string.hong_kong_description
)

fun getCountriesList(): List<CountryDvo> {
    return listOf(
        andorra, portugal, spain,
        poland, slovakia, slovenia,
        ukraine, estonia, latvia,
        turkey, georgia, qatar,
        kazakhstan, kyrgyzstan, tajikistan,
        japan, southKorea, hongKong
    )
}