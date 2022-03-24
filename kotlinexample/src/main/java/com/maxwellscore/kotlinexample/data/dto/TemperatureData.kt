package com.maxwellscore.kotlinexample.data.dto

import com.google.gson.annotations.SerializedName
import com.maxwellscore.kotlinexample.data.DataConstants

data class TemperatureData(
    @SerializedName(DataConstants.KEY_TEMPERATURE)
    val temperature: Int,
    @SerializedName(DataConstants.KEY_UNIT)
    val unit: String
)
