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
        initRecyclerView()
        setupDialogFragmentListener()

    }

    private fun initUi() {
        title = view?.findViewById(R.id.edit_text_title)

        initToolbar()
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
        noteRecyclerView?.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        Log.d(ownerContentId.toString()," CONTENTid")
        ownerContentId?.let { noteViewModel.getNoteDvo(it) }
        noteViewModel.noteListLiveData.observe(activity as LifecycleOwner, {
            if (it.isNotEmpty()){
                Log.d(it.toString()," CONTENTidList")
                noteRecyclerView?.adapter = NoteAdaptor(it)
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

    fun setText(noteId: Long, text: String){
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

/*
    @SuppressLint("NotifyDataSetChanged")
    override fun onItemTextClick(position: Int, noteId: Long, text: String) {
        noteViewModel.setTextNote(noteId, text)
        ownerContentId?.let { noteViewModel.createNote(it) }
        //noteRecyclerView.adapter?.notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onItemCompletedClick(position: Int, noteId: Long) {
        noteViewModel.setIsCompletedNote(noteId, true)
        //noteRecyclerView.adapter?.notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onItemBinClick(position: Int, noteId: Long) {
        noteViewModel.deleteNote(noteId)
        //noteRecyclerView.adapter?.notifyDataSetChanged()
    }

 */
}