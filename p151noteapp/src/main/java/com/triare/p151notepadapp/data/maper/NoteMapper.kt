package com.triare.p151notepadapp.data.maper

import com.triare.p151notepadapp.data.db.model.NoteDbo
import com.triare.p151notepadapp.ui.dvo.NoteDvo

class NoteMapper(private val noteDbo: List<NoteDbo>) {

    fun map(): List<NoteDvo> {
        return noteDbo.map {
            NoteDvo(
                it.text,
                it.completed,
                it.noteId
            )
        }
    }
}