package com.triare.p151notes.ui.content

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.triare.p151notes.ui.MainActivity
import com.triare.p151notes.ui.adaptors.ContentAdaptor
import com.triare.p151notes.R
import com.triare.p151notes.ui.models.ContentViewModel
import com.triare.p151notes.ui.note.NoteFragment

class ContentFragment : Fragment(), ContentAdaptor.OnItemClickListener {

    private val contentViewModel: ContentViewModel by activityViewModels()
    private lateinit var contentAdaptor: ContentAdaptor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_content, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()

        val btnAddNote: FloatingActionButton = view.findViewById(R.id.btn_add_note)
        btnAddNote.setOnClickListener {
            contentViewModel.createContent()
            contentViewModel.getCreatedContentId()
            contentViewModel.contentIdLiveData.observe(viewLifecycleOwner, {
                (requireActivity() as MainActivity).replaceFragment(NoteFragment.newInstance(it))
                contentViewModel.createNote(it)
            })
        }
    }
    
    override fun onStart() {
        super.onStart()
        contentViewModel.getContentDvo()
    }

    private fun initUi() {
        initRecyclerView()
        addDataSet()
    }

    private fun initRecyclerView() {

        val contentRecyclerView = requireView().findViewById<RecyclerView>(R.id.recycler_view_content)
        contentRecyclerView.apply {
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            contentAdaptor = ContentAdaptor(this@ContentFragment)
            adapter = contentAdaptor
        }
    }

    private fun addDataSet(){
        Log.d("DATA_CONTENT_REC", "TUT")
        contentViewModel.contentListLiveData.observe(activity as LifecycleOwner, {
            Log.d("DATA_CONTENT_REC", it.toString())
            if (it.isNotEmpty()){
                contentAdaptor.submitContentList(it)
                Log.d("DATA_CONTENT_REC", it.toString())
            }
        })
    }


    companion object {
        fun newInstance() = ContentFragment()
    }

    override fun onItemClick(position: Int, contentId: Long) {
        val noteFragment = NoteFragment.newInstance(contentId)
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_fragment, noteFragment)
            .addToBackStack("test")
            .commit()
    }
}