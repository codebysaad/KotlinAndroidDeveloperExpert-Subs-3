package xyz.webflutter.kadefootballlanguage.model

import com.google.gson.annotations.SerializedName

data class TeamResponse(
    @SerializedName("teams")
    val bdageTeam: List<ModelTeams>,

    @SerializedName("team")
    val responses: List<ModelTeams>
)