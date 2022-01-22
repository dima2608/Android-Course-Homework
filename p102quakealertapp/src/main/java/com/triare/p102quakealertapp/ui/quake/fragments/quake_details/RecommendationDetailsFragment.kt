package com.triare.p102quakealertapp.ui.quake.fragments.quake_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.triare.p102quakealertapp.R
import com.triare.p102quakealertapp.data.api.model.FeaturesItem
import com.triare.p102quakealertapp.ui.quake.dvo.FeatureQuakeDvo

class RecommendationDetailsFragment : Fragment() {

    private var dataDetailsQuake: FeatureQuakeDvo? = null
    private var timeQuake: TextView? = null
    private var locationQuake: TextView? = null
    private var intensity: TextView? = null
    private var magnitude: TextView? = null
    private var hide: ImageView? = null
    private var wait: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initArgs()
    }

    private fun initArgs() {
        arguments?.let {
            if (it.containsKey(KEY_DATA_ARGS)) {
                dataDetailsQuake = arguments?.get(KEY_DATA_ARGS) as FeatureQuakeDvo?
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recommendation_details, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUi()
        renderUi()
    }

    private fun initUi() {
        timeQuake = view?.findViewById(R.id.whenTime)
        locationQuake = view?.findViewById(R.id.where)
        intensity = view?.findViewById(R.id.quake_intensity)
        magnitude = view?.findViewById(R.id.quake_magnitude)
        hide = view?.findViewById(R.id.hide)
        wait = view?.findViewById(R.id.wait)
    }

    private fun renderUi(){
        timeQuake?.text = dataDetailsQuake?.dateTime
        locationQuake?.text = dataDetailsQuake?.location
        dataDetailsQuake?.intensityTitle?.let { intensity?.setText(it) }
        dataDetailsQuake?.intensityColor?.let { intensity?.setBackgroundResource(it) }
        magnitude?.text = dataDetailsQuake?.magnitude

        if (dataDetailsQuake?.intensityTitle != R.string.very_strong_intensity_title) {
            hide?.visibility = View.GONE
            wait?.visibility = View.GONE
        }
    }

    companion object {
        private const val KEY_DATA_ARGS = "FeatureQuakeDvo"
        fun newInstance(dataDetailsQuake: FeatureQuakeDvo): RecommendationDetailsFragment {
            val args = bundleOf(KEY_DATA_ARGS to dataDetailsQuake)

            val detailsFragment = RecommendationDetailsFragment()
            detailsFragment.arguments = args

            return detailsFragment
        }
    }
}