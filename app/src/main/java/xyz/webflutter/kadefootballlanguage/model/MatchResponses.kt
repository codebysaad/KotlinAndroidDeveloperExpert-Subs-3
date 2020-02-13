package xyz.webflutter.kadefootballlanguage.model

import com.google.gson.annotations.SerializedName

data class MatchResponses (
    @SerializedName("events")
    val matches: List<ModelMatch>,
    @SerializedName("event")
    val search: List<ModelMatch>
)