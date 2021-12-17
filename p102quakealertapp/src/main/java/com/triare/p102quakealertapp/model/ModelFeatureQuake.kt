package com.triare.p102quakealertapp.model

import com.google.gson.annotations.SerializedName

data class Response(

	@SerializedName("features")
	val features: List<FeaturesItem>,

	@SerializedName("type")
	val type: String
)

data class FeaturesItem(

	@SerializedName("geometry")
	val geometry: Geometry,

	@SerializedName("type")
	val type: String,

	@SerializedName("properties")
	val properties: Properties
)

data class Properties(

	@SerializedName("depth")
	val depth: Double,

	@SerializedName("mmi")
	val mmi: Int,

	@SerializedName("locality")
	val locality: String,

	@SerializedName("magnitude")
	val magnitude: Double,

	@SerializedName("time")
	val time: String,

	@SerializedName("publicID")
	val publicID: String,

	@SerializedName("quality")
	val quality: String
)

data class Geometry(

	@SerializedName("coordinates")
	val coordinates: List<Double>,

	@SerializedName("type")
	val type: String
)
