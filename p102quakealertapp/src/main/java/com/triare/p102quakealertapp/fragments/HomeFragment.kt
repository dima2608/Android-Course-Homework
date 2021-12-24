package com.triare.p102quakealertapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.triare.p102quakealertapp.QuakeViewModel
import com.triare.p102quakealertapp.R
import com.triare.p102quakealertapp.adaptor.QuakeAdaptor
import com.triare.p102quakealertapp.model.FeaturesItem

class HomeFragment : Fragment(), QuakeAdaptor.OnItemClickListener {

    private val dataModel: QuakeViewModel by activityViewModels()
    private var listOfQuake: List<FeaturesItem>? = null

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
        recyclerViewStories.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        dataModel.getQuakeResultLiveData().observe(activity as LifecycleOwner, {
            recyclerViewStories.adapter = QuakeAdaptor(it.features, this)
            listOfQuake = it.features
        })
    }

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun onItemClick(position: Int, data: FeaturesItem) {
        val detailsFragment = RecommendationDetailsFragment
            .newInstance(data)
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_fragment, detailsFragment)
            .addToBackStack("item")
            .commit()
        Log.d("TEST1", data.toString())

    }

}

