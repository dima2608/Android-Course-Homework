package com.triare.p151notepadapp.data.maper


import com.triare.p151notepadapp.data.db.model.ContentDbo
import com.triare.p151notepadapp.data.repository.NotepadRepository
import com.triare.p151notepadapp.ui.dvo.ContentDvo
import com.triare.p151notepadapp.utisl.TimeUtils

class ContentMapper(private val contentDbo: List<ContentDbo>) {

    fun map(): List<ContentDvo>{
        return contentDbo.map {
            ContentDvo(
                it.title,
                TimeUtils.convertLongToTime(it.lastUpdateMillis),
                NotepadRepository().getFractions(it.contentId),
                it.contentId
            )
        }
    }
}