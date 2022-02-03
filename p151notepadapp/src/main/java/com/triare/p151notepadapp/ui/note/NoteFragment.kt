package com.triare.p151notepadapp.ui.note

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.appcompat.widget.ContentFrameLayout
import androidx.appcompat.widget.Toolbar
import androidx.core.os.bundleOf
import androidx.fragment.app.FragmentResultListener

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.triare.p151notepadapp.R
import com.triare.p151notepadapp.ui.adaptors.ContentAdaptor
import com.triare.p151notepadapp.ui.adaptors.NoteAdaptor
import com.triare.p151notepadapp.ui.content.ContentFragment
import com.triare.p151notepadapp.ui.dialog.DeleteDialogFragment
import com.triare.p151notepadapp.ui.models.NoteViewModel

class NoteFragment : Fragment() {

    private val noteViewModel: NoteViewModel by activityViewModels()
    private var toolbar: Toolbar? = null
    private var ownerContentId: Long? = null
    private var title: EditText? = null
    private lateinit var noteAdaptor: NoteAdaptor


    override fun onCreate(savedInstanceState: Bundle?) {
        setHasOptionsMenu(true)
        super.onCreate(savedInstanceState)

        initArgs()
    }

    private fun initArgs() {
        arguments?.let {
            if (it.containsKey(KEY_DATA_ARGS)) {
                ownerContentId = arguments?.get(KEY_DATA_ARGS) as Long?
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_note, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        setupDialogFragmentListener()

        val btnAddNote: FloatingActionButton = view.findViewById(R.id.btn_add_note_note)
        btnAddNote.setOnClickListener {
            ownerContentId?.let { contentId -> noteViewModel.createNote(contentId) }
            addDataSet()
        }

    }

    private fun initUi() {

        title = view?.findViewById(R.id.edit_text_title)
        title?.hint = "Add a title"
        ownerContentId?.let { noteViewModel.getTitle(it) }
        noteViewModel.tileLiveData.observe(viewLifecycleOwner, {
            Log.d(it, "TITLE")
            title?.setText(it)
        })

        initToolbar()
        initRecyclerView()
        addDataSet()
    }

    private fun initToolbar() {
        toolbar = view?.findViewById(R.id.top_app_bar)

        initToolbarNavigation()
        initToolbarMenu()
    }

    private fun initToolbarNavigation() {
        toolbar?.setNavigationIcon(R.drawable.ic_arrow_back)
        toolbar?.setNavigationOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }
    }

    private fun initToolbarMenu() {
        toolbar?.inflateMenu(R.menu.menu)
        toolbar?.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.btn_done -> {
                    if (title?.text?.length != 0) {
                        ownerContentId?.let { contentId ->
                            noteViewModel.setTitle(
                                contentId,
                                title?.text.toString()
                            )
                        }
                    } else
                        title?.hint = "Add a title"
                    true
                }
                R.id.btn_bin -> {
                    showDeleteDialogFragment()
                    true
                }
                else -> {
                    super.onOptionsItemSelected(it)
                }
            }
        }
    }

    private fun initRecyclerView() {
        val noteRecyclerView = view?.findViewById<RecyclerView>(R.id.recycler_view_note)
        noteRecyclerView?.apply {
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            noteAdaptor = NoteAdaptor()
            adapter = noteAdaptor
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun addDataSet(){
        ownerContentId?.let { noteViewModel.getNoteDvo(it) }
        noteViewModel.noteListLiveData.observe(activity as LifecycleOwner, {
            if (it.isNotEmpty()){
                noteAdaptor.submitNoteList(it)
            }
        })
    }

    private fun showDeleteDialogFragment() {
        val dialogFragment = DeleteDialogFragment()
        dialogFragment.show(parentFragmentManager, DeleteDialogFragment.TAG)
    }

    private fun setupDialogFragmentListener() {
        parentFragmentManager.setFragmentResultListener(
            DeleteDialogFragment.REQUEST_KEY,
            viewLifecycleOwner,
            FragmentResultListener { _, result ->
                when (result.getInt(DeleteDialogFragment.KEY_RESPONSE)) {
                    DialogInterface.BUTTON_POSITIVE -> {
                        ownerContentId?.let { noteViewModel.deleteContent(it) }
                    }
                }
            })
    }

    fun setText(noteId: Long, text: String) {
        noteViewModel.setTextNote(noteId, text)
    }

    companion object {
        private const val KEY_DATA_ARGS = "ownerContentId"
        fun newInstance(ownerContentId: Long): NoteFragment {
            val args = bundleOf(KEY_DATA_ARGS to ownerContentId)

            val noteFragment = NoteFragment()
            noteFragment.arguments = args

            return noteFragment
        }
    }
}