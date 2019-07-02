package com.company.driver.temp

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("hits")
    @Expose
    var hits: List<Hit>? = null,
    @SerializedName("nbHits")
    @Expose
    var nbHits: Long? = null,
    @SerializedName("page")
    @Expose
    var page: Long? = null,
    @SerializedName("nbPages")
    @Expose
    var nbPages: Long? = null,
    @SerializedName("hitsPerPage")
    @Expose
    var hitsPerPage: Long? = null,
    @SerializedName("processingTimeMS")
    @Expose
    var processingTimeMS: Long? = null,
    @SerializedName("exhaustiveNbHits")
    @Expose
    var exhaustiveNbHits: Boolean? = null,
    @SerializedName("query")
    @Expose
    var query: String? = null,
    @SerializedName("params")
    @Expose
    var params: String? = null
)