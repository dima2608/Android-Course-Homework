package com.triare.p151notes.data.maper

import com.triare.p151notes.data.db.model.NoteDbo
import com.triare.p151notes.ui.dvo.NoteDvo

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