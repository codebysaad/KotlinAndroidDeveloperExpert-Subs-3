package xyz.webflutter.kadefootballlanguage.db

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class FavoriteDatabase(
    var id: Long?,
    var strEvent: String?,
    var idEvent: String?,
    var dateEvent: String?,
    var idAwayTeam: String?,
    var intAwayScore: String?,
    var intAwayShots: String?,
    var strAwayFormation: String?,
    var strAwayLineupDefense: String?,
    var strAwayLineupForward: String?,
    var strAwayLineupGoalkeeper: String?,
    var strAwayLineupMidfield: String?,
    var strAwayLineupSubstitutes: String?,
    var strAwayRedCards: String?,
    var strAwayTeam: String?,
    var strAwayYellowCards: String?,
    var idHomeTeam: String?,
    var intHomeScore: String?,
    var intHomeShots: String?,
    var strHomeFormation: String?,
    var strHomeLineupDefense: String?,
    var strHomeLineupForward: String?,
    var strHomeLineupGoalkeeper: String?,
    var strHomeLineupMidfield: String?,
    var strHomeLineupSubstitutes: String?,
    var strHomeRedCards: String?,
    var strHomeTeam: String?,
    var strHomeYellowCards: String?,
    var strTime: String?,
    var strSport: String?,
    var strDate: String?,
    var strDescriptionEN: String?
) : Parcelable {
    companion object {
        const val ID: String = "ID_"
        const val TABLE_FAVORITE: String = "TABLE_FAVORITE"
        const val STR_EVENT: String = "STR_EVENT"
        const val ID_EVENT: String = "ID_EVENT"
        const val DATE_EVENT: String = "DATE_EVENT"
        const val TIME_EVENT: String = "TIME_EVENT"
        const val STR_HOME_TEAM: String = "STR_HOME_TEAM"
        const val STR_AWAY_TEAM: String = "STR_AWAY_TEAM"
        const val INT_HOME_SCORE: String = "INT_HOME_SCORE"
        const val INT_AWAY_SCORE: String = "INT_AWAY_SCORE"
        const val INT_HOME_SHOOTS: String = "INT_HOME_SHOOTS"
        const val INT_AWAY_SHOOTS: String = "INT_AWAY_SHOOTS"
        const val STR_HOME_YELLOW: String = "HOME_YELLOW_CARDS"
        const val STR_AWAY_YELLOW: String = "AWAY_YELLOW_CARDS"
        const val STR_HOME_RED: String = "HOME_RED_CARDS"
        const val STR_AWAY_RED: String = "AWAY_RED_CARDS"
        const val STR_HOME_FORMATION: String = "HOME_FORMATION"
        const val STR_AWAY_FORMATION: String = "AWAY_FORMATION"
        const val STR_HOME_GOAL_KEEPER: String = "HOME_GOAL_KEEPER"
        const val STR_AWAY_GOAL_KEEPER: String = "AWAY_GOAL_KEEPER"
        const val STR_HOME_DEFENSE: String = "HOME_DEFENSE"
        const val STR_AWAY_DEFENSE: String = "AWAY_DEFENSE"
        const val STR_HOME_MID: String = "HOME_MID"
        const val STR_AWAY_MID: String = "AWAY_MID"
        const val STR_HOME_FORWARD: String = "HOME_FORWARD"
        const val STR_AWAY_FORWARD: String = "AWAY_FORWARD"
        const val STR_HOME_SUBTITUTES: String = "HOME_SUBTITUTES"
        const val STR_AWAY_SUBTITUTES: String = "AWAY_SUBTITUTES"
        const val ID_HOME_TEAM: String = "ID_HOME_TEAM"
        const val ID_AWAY_TEAM: String = "ID_AWAY_TEAM"
        const val STR_SPORT: String = "STR_SPORT"
        const val STR_DATE: String = "STR_DATE"
        const val DESCRIPTION_EN: String = "DESCRIPTION_EN"
    }
}