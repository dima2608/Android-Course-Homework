package com.triare.p102quakealertapp.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import com.triare.p102quakealertapp.R
import com.triare.p102quakealertapp.adaptor.QuakeIntensity
import com.triare.p102quakealertapp.model.FeaturesItem

class RecommendationDetailsFragment : Fragment() {

    private var dataDetailsQuake: FeaturesItem? = null
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
                dataDetailsQuake = arguments?.get(KEY_DATA_ARGS) as FeaturesItem?
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
    }

    @SuppressLint("ResourceType")
    private fun initUi() {
        val intensityData = initIntensity(dataDetailsQuake?.properties!!.magnitude)
        timeQuake = view?.findViewById(R.id.whenTime)
        locationQuake = view?.findViewById(R.id.where)
        intensity = view?.findViewById(R.id.quake_intensity)
        magnitude = view?.findViewById(R.id.quake_magnitude)
        hide = view?.findViewById(R.id.hide)
        wait = view?.findViewById(R.id.wait)

        timeQuake?.text = dataDetailsQuake?.properties?.time
        locationQuake?.text = dataDetailsQuake?.properties?.locality
        intensity?.setText(intensityData.title)
        intensity?.setBackgroundResource(intensityData.color)
        magnitude?.text = String.format("%.2f", dataDetailsQuake?.properties!!.magnitude)

        if (intensityData.title != R.string.very_strong_intensity_title) {
            hide?.visibility = View.GONE
            wait?.visibility = View.GONE
        }

    }

    companion object {
        private const val KEY_DATA_ARGS = "FeaturesItem"
        fun newInstance(dataDetailsQuake: FeaturesItem): RecommendationDetailsFragment {
            val args = bundleOf(KEY_DATA_ARGS to dataDetailsQuake)

            val detailsFragment = RecommendationDetailsFragment()
            detailsFragment.arguments = args

            return detailsFragment
        }

        fun initIntensity(intensity: Double): QuakeIntensity {
            return when (intensity) {
                in 1.0..1.99 -> QuakeIntensity.BARELY_NOTICEABLE
                in 2.0..2.99 -> QuakeIntensity.WEAK
                in 3.0..3.99 -> QuakeIntensity.AVERAGE
                in 4.0..4.99 -> QuakeIntensity.STRONG
                else -> QuakeIntensity.VERY_STRONG
            }
        }

    }
}