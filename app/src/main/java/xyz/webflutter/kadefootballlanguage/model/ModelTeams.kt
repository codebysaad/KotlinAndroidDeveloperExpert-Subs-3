package xyz.webflutter.kadefootballlanguage.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ModelTeams(
    @SerializedName("idTeam")
    var idTeam: String?,
    @SerializedName("strTeam")
    var strTeam: String?,
    @SerializedName("strTeamShort")
    var strTeamShort: String?,
    @SerializedName("strAlternate")
    var strAlternate: String?,
    @SerializedName("intFormedYear")
    var intFormedYear: String?,
    @SerializedName("strLeague")
    var strLeague: String?,
    @SerializedName("idLeague")
    var idLeague: String?,
    @SerializedName("strManager")
    var strManager: String?,
    @SerializedName("strStadium")
    var strStadium: String?,
    @SerializedName("strStadiumThumb")
    var strStadiumThumb: String?,
    @SerializedName("strStadiumDescription")
    var strStadiumDescription: String?,
    @SerializedName("strStadiumLocation")
    var strStadiumLocation: String?,
    @SerializedName("intStadiumCapacity")
    var intStadiumCapacity: String?,
    @SerializedName("strDescriptionEN")
    var strDescriptionEN: String?,
    @SerializedName("strCountry")
    var strCountry: String?,
    @SerializedName("strTeamBadge")
    var strTeamBadge: String?,
    @SerializedName("strTeamJersey")
    var strTeamJersey: String?,
    @SerializedName("strTeamLogo")
    var strTeamLogo: String?,
    @SerializedName("strTeamBanner")
    var strTeamBanner: String?
) : Parcelable