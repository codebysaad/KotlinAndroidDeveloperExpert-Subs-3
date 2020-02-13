package xyz.webflutter.kadefootballlanguage.db

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeamsDatabase(
    var id: Long?,
    var idTeam: String?,
    var strTeam: String?,
    var strTeamShort: String?,
    var strAlternate: String?,
    var intFormedYear: String?,
    var strLeague: String?,
    var idLeague: String?,
    var strManager: String?,
    var strStadium: String?,
    var strStadiumThumb: String?,
    var strStadiumDescription: String?,
    var strStadiumLocation: String?,
    var intStadiumCapacity: String?,
    var strDescriptionEN: String?,
    var strCountry: String?,
    var strTeamBadge: String?,
    var strTeamJersey: String?,
    var strTeamLogo: String?,
    var strTeamBanner: String?
) : Parcelable {
    companion object {
        const val ID: String = "ID_"
        const val TABLE_TEAMS: String = "TABLE_TEAMS"
        const val ID_TEAM: String = "ID_TEAM"
        const val STR_TEAM: String = "STR_TEAM"
        const val STR_TEAM_SHORT: String = "STR_TEAM_SHORT"
        const val STR_ALTERNATE: String = "STR_ALTERNATE"
        const val INT_FORMED_YEAR: String = "INT_FORMED_YEAR"
        const val STR_LEAGUE: String = "STR_LEAGUE"
        const val ID_LEAGUE: String = "ID_LEAGUE"
        const val STR_MANAGER: String = "STR_MANAGER"
        const val STR_STADIUM: String = "STR_STADIUM"
        const val STR_STADIUM_THUMB: String = "STR_STADIUM_THUMB"
        const val STR_STADIUM_DESCRIPTION: String = "STR_STADIUM_DESCRIPTION"
        const val STR_STADIUM_LOCATION: String = "STR_STADIUM_LOCATION"
        const val INT_STADIUM_CAPACITY: String = "INT_STADIUM_CAPACITY"
        const val STR_DESCRIPTION_EN: String = "STR_DESCRIPTION_EN"
        const val STR_COUNTRY: String = "STR_COUNTRY"
        const val STR_TEAM_BADGE: String = "STR_TEAM_BADGE"
        const val STR_TEAM_JERSEY: String = "STR_TEAM_JERSEY"
        const val STR_TEAM_LOGO: String = "STR_TEAM_LOGO"
        const val STR_TEAM_BANNER: String = "STR_TEAM_BANNER"
    }
}