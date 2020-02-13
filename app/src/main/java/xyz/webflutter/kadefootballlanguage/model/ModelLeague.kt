package xyz.webflutter.kadefootballlanguage.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class ModelLeague(
    @SerializedName("idLeague")
    var idLeague: String,
    @SerializedName("strLeague")
    var strLeague: String,
    @SerializedName("strLeagueAlternate")
    var strLeagueAlternate: String,
    @SerializedName("intFormedYear")
    var intFormedYear: String,
    @SerializedName("dateFirstEvent")
    var dateFirstEvent: String,
    @SerializedName("strGender")
    var strGender: String,
    @SerializedName("strCountry")
    var strCountry: String,
    @SerializedName("strWebsite")
    var strWebsite: String,
    @SerializedName("strFacebook")
    var strFacebook: String,
    @SerializedName("strTwitter")
    var strTwitter: String,
    @SerializedName("strYoutube")
    var strYoutube: String,
    @SerializedName("strDescriptionEN")
    var strDescriptionEN: String
) : Parcelable