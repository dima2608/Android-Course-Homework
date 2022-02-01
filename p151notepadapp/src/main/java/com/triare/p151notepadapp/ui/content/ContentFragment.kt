package com.triare.p151notepadapp.ui.content

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.triare.p151notepadapp.R
import com.triare.p151notepadapp.ui.MainActivity
import com.triare.p151notepadapp.ui.adaptors.ContentAdaptor
import com.triare.p151notepadapp.ui.models.ContentViewModel
import com.triare.p151notepadapp.ui.note.NoteFragment


class ContentFragment : Fragment(), ContentAdaptor.OnItemClickListener {

    private val contentViewModel: ContentViewModel by activityViewModels()

    //lateinit var contentRecyclerView: RecyclerView
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
        contentViewModel.contentListLiveData.observe(activity as LifecycleOwner, {
            Log.d("DATA1", it.toString())
        })

        val btnAddNote: FloatingActionButton = view.findViewById(R.id.btn_add_note)
        btnAddNote.setOnClickListener {
            contentViewModel.createContent()
            contentViewModel.getCreatedContentId()
            contentViewModel.contentIdLiveData.observe(viewLifecycleOwner, {
                (requireActivity() as MainActivity).replaceFragment(NoteFragment.newInstance(it))
            })
        }
    }
    
    override fun onStart() {
        super.onStart()
        contentViewModel.getContentDvo()
    }

    private fun initUi() {
        initRecyclerView()


    }

    private fun initRecyclerView() {

        val contentRecyclerView = requireView().findViewById<RecyclerView>(R.id.recycler_view_content)
        contentRecyclerView.apply {
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            contentAdaptor = ContentAdaptor(this@ContentFragment)
            adapter = contentAdaptor
        }
        contentViewModel.contentListLiveData.observe(activity as LifecycleOwner, {
            Log.d("DATA_CONTENT_REC", it.toString())
            if (it.isNotEmpty()){
                contentAdaptor.submitContentList(it)
                Log.d("DATA_CONTENT_REC", it.toString())
            }
        })


        /*
        contentRecyclerView = requireView().findViewById<RecyclerView>(R.id.recycler_view_content)
        contentRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        contentViewModel.contentListLiveData.observe(activity as LifecycleOwner, {
            if (it.isNotEmpty()){
                contentRecyclerView.adapter = ContentAdaptor(this).submitContentList(it)

            }
        })

         */
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