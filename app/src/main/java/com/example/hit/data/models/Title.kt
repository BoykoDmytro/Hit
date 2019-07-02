package com.example.hit.data.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Title(
    @SerializedName("value")
    @Expose
    var value: String? = null,
    @SerializedName("matchLevel")
    @Expose
    var matchLevel: String? = null,
    @SerializedName("matchedWords")
    @Expose
    var matchedWords: List<Any>? = null
)