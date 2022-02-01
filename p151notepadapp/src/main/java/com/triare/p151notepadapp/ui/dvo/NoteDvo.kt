package com.triare.p151notepadapp.ui.dvo

data class NoteDvo(
    var text: String,
    var completed: Boolean,
    var noteId: Long
){
    override fun equals(other: Any?): Boolean {

        if (javaClass !== other?.javaClass){
            return false
        }

        other as NoteDvo
        if (text != other.text)
            return false

        if (completed != other.completed)
            return false

        if (noteId != other.noteId)
            return false

        return true
    }
}