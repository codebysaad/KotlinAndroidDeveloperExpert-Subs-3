package xyz.webflutter.kadefootballlanguage.model

import com.google.gson.annotations.SerializedName

data class LeagueResponse (
    @SerializedName("leagues")
    val detailLeague: List<ModelLeague>
)