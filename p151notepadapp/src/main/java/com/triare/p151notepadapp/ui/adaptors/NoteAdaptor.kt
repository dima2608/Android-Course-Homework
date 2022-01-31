package com.triare.p151notepadapp.ui.adaptors

import android.graphics.Paint
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.triare.p151notepadapp.R
import com.triare.p151notepadapp.ui.MainActivity
import com.triare.p151notepadapp.ui.content.ContentFragment
import com.triare.p151notepadapp.ui.dvo.NoteDvo
import com.triare.p151notepadapp.ui.note.NoteFragment


class NoteAdaptor(
    private val items: List<NoteDvo>
    //private val
) : RecyclerView.Adapter<NoteAdaptor.NoteViewHolder>() {

    private var textNote: String? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_note, parent, false)
        return NoteViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val text = itemView.findViewById<EditText>(R.id.note_text)
        private val completed = itemView.findViewById<CheckBox>(R.id.btn_checkbox)
        private val bin = itemView.findViewById<ImageView>(R.id.btn_note_bin)

        fun bind(data: NoteDvo) {

            if (data.text.isEmpty()) {
                text.hint = "Tap here to add a note"
            }

            if (data.completed) {
                completed.isChecked = true
                text.setText(data.text)
                text.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            } else {
                completed.isChecked = false
                text.setText(data.text)
                text.paintFlags = Paint.ANTI_ALIAS_FLAG
            }
            text.afterTextChanged {
                //NoteFragment().setText(data.noteId, text.text.toString())
                Log.d("ADAPTOR", text.text.toString())
            }
/*
            text.setOnKeyListener(object : View.OnKeyListener {
                override fun onKey(v: View?, keyCode: Int, event: KeyEvent?): Boolean {
                    if (event != null) {
                        if (event.action == EditorInfo.IME_ACTION_DONE &&
                            keyCode == KeyEvent.KEYCODE_ENTER
                        ) {
                            NoteFragment().setText(data.noteId, text.text.toString())
                            Log.d("ADAPTOR", text.text.toString())

                            text.clearFocus()
                            text.isCursorVisible = false
                            return true
                        }
                    }
                    return false
                }
            })

 */
            completed.setOnClickListener {
                if (completed.isChecked) {
                    text.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                } else
                    text.paintFlags = Paint.ANTI_ALIAS_FLAG
            }
        }
    }

    private fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
        this.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                afterTextChanged.invoke(s.toString())
            }

            override fun afterTextChanged(editable: Editable?) {
                //afterTextChanged.invoke(editable.toString())
            }
        })

    }
}