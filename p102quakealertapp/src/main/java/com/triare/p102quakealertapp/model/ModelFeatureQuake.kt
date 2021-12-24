package com.triare.p102quakealertapp.model

import com.google.gson.annotations.SerializedName

data class QuakeDto(

	@SerializedName("features")
	val features: List<FeaturesItem>,

	@SerializedName("type")
	val type: String
): java.io.Serializable

data class FeaturesItem(

	@SerializedName("geometry")
	val geometry: Geometry,

	@SerializedName("type")
	val type: String,

	@SerializedName("properties")
	val properties: Properties
): java.io.Serializable

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
): java.io.Serializable

data class Geometry(

	@SerializedName("coordinates")
	val coordinates: List<Double>,

	@SerializedName("type")
	val type: String
): java.io.Serializable
