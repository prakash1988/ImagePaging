package com.example.imagepagelist.model

import com.google.gson.annotations.SerializedName
import java.math.BigInteger

data class Photo (

	@SerializedName("id") val id : BigInteger,
	@SerializedName("owner") val owner : String,
	@SerializedName("secret") val secret : String,
	@SerializedName("server") val server : Int,
	@SerializedName("farm") val farm : Int,
	@SerializedName("title") val title : String,
	@SerializedName("ispublic") val ispublic : Int,
	@SerializedName("isfriend") val isfriend : Int,
	@SerializedName("isfamily") val isfamily : Int
)