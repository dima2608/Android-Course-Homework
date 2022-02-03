package com.triare.p151notepadapp.ui.content

import android.annotation.SuppressLint
import android.os.Bundle
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

    private var iconAddNote: ImageView? = null
    lateinit var contentRecyclerView: RecyclerView

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
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        initRecyclerView()
        contentRecyclerView.adapter?.notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onStart() {
        super.onStart()
        initRecyclerView()
        contentRecyclerView.adapter?.notifyDataSetChanged()
    }

    private fun initUi() {
        initRecyclerView()

    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initRecyclerView() {
        contentRecyclerView = requireView().findViewById<RecyclerView>(R.id.recycler_view_content)
        contentRecyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        contentViewModel.contentListLiveData.observe(activity as LifecycleOwner, {
            if (it.isNotEmpty()){
                contentRecyclerView.adapter = ContentAdaptor(it, this)
                contentRecyclerView.adapter!!.notifyDataSetChanged()
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