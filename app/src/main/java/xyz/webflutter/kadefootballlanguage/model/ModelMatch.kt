package xyz.webflutter.kadefootballlanguage.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ModelMatch(
    @SerializedName("strEvent")
    var strEvent: String,
    @SerializedName("idEvent")
    var idEvent: String,
    @SerializedName("dateEvent")
    var dateEvent: String,
    @SerializedName("idAwayTeam")
    var idAwayTeam: String,
    @SerializedName("intAwayScore")
    var intAwayScore: String,
    @SerializedName("intAwayShots")
    var intAwayShots: String,
    @SerializedName("strAwayFormation")
    var strAwayFormation: String,
    @SerializedName("strAwayLineupDefense")
    var strAwayLineupDefense: String,
    @SerializedName("strAwayLineupForward")
    var strAwayLineupForward: String,
    @SerializedName("strAwayLineupGoalkeeper")
    var strAwayLineupGoalkeeper: String,
    @SerializedName("strAwayLineupMidfield")
    var strAwayLineupMidfield: String,
    @SerializedName("strAwayLineupSubstitutes")
    var strAwayLineupSubstitutes: String,
    @SerializedName("strAwayRedCards")
    var strAwayRedCards: String,
    @SerializedName("strAwayTeam")
    var strAwayTeam: String,
    @SerializedName("strAwayYellowCards")
    var strAwayYellowCards: String,
    @SerializedName("idHomeTeam")
    var idHomeTeam: String,
    @SerializedName("intHomeScore")
    var intHomeScore: String,
    @SerializedName("intHomeShots")
    var intHomeShots: String,
    @SerializedName("strHomeFormation")
    var strHomeFormation: String,
    @SerializedName("strHomeLineupDefense")
    var strHomeLineupDefense: String,
    @SerializedName("strHomeLineupForward")
    var strHomeLineupForward: String,
    @SerializedName("strHomeLineupGoalkeeper")
    var strHomeLineupGoalkeeper: String,
    @SerializedName("strHomeLineupMidfield")
    var strHomeLineupMidfield: String,
    @SerializedName("strHomeLineupSubstitutes")
    var strHomeLineupSubstitutes: String,
    @SerializedName("strHomeRedCards")
    var strHomeRedCards: String,
    @SerializedName("strHomeTeam")
    var strHomeTeam: String,
    @SerializedName("strHomeYellowCards")
    var strHomeYellowCards: String,
    @SerializedName("strTime")
    var strTime: String,
    @SerializedName("strSport")
    var strSport: String,
    @SerializedName("strDate")
    var strDate: String,
    @SerializedName("strDescriptionEN")
    var strDescriptionEN: String
):Parcelable