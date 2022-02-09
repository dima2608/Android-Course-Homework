package com.triare.p151notes.ui.dvo

data class ContentDvo(
    var title: String,
    var dataTime: String,
    var fractions: String,
    val contentId: Long
){
    override fun equals(other: Any?): Boolean {

        if (javaClass !== other?.javaClass){
            return false
        }

        other as ContentDvo
        if (title != other.title)
            return false

        if (dataTime != other.dataTime)
            return false

        if (fractions != other.fractions)
            return false

        if (contentId != other.contentId)
            return false

        return true
    }
}

