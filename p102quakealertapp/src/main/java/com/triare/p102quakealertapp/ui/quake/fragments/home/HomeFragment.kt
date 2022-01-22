package com.triare.p102quakealertapp.ui.quake.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.triare.p102quakealertapp.ui.quake.fragments.model.QuakeViewModel
import com.triare.p102quakealertapp.R
import com.triare.p102quakealertapp.ui.quake.fragments.home.adaptor.QuakeAdaptor
import com.triare.p102quakealertapp.ui.quake.fragments.quake_details.RecommendationDetailsFragment
import com.triare.p102quakealertapp.data.api.model.FeaturesItem
import com.triare.p102quakealertapp.ui.quake.dvo.FeatureQuakeDvo

class HomeFragment : Fragment(), QuakeAdaptor.OnItemClickListener {

    private val dataModel: QuakeViewModel by activityViewModels()
    private var listOfQuake: List<FeatureQuakeDvo>? = null

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

        dataModel.quakeResultLiveData.observe(activity as LifecycleOwner, {
            recyclerViewStories.adapter = QuakeAdaptor(it,this)
            listOfQuake = it
        })
    }

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun onItemClick(position: Int, data: FeatureQuakeDvo) {
        val detailsFragment = RecommendationDetailsFragment.newInstance(data)
        requireActivity().supportFragmentManager
            .beginTransaction()
            .replace(R.id.nav_host_fragment, detailsFragment)
            .addToBackStack("item")
            .commit()
    }

}

