package com.triare.p102quakealertapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.triare.p102quakealertapp.MainActivity
import com.triare.p102quakealertapp.QuakeViewModel
import com.triare.p102quakealertapp.R
import com.triare.p102quakealertapp.adaptor.QuakeAdaptor
import com.triare.p102quakealertapp.model.QuakeDto

class HomeFragment : Fragment(), QuakeAdaptor.OnItemClickListener{

    private val dataModel: QuakeViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerViewStories = view.findViewById<RecyclerView>(R.id.recycler_view_home)
        recyclerViewStories.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        dataModel.getQuakeResultLiveData().observe(activity as LifecycleOwner, Observer {
            recyclerViewStories.adapter = QuakeAdaptor(it.features, this)
        })
    }

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun onItemClick(position: Int) {
        Log.d("Daa", "Item $position")
    }

}

