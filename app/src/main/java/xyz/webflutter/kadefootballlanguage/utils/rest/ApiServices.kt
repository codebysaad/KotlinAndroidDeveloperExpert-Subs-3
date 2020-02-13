package xyz.webflutter.kadefootballlanguage.utils.rest

import android.net.Uri
import xyz.webflutter.kadefootballlanguage.BuildConfig.API_KEY
import xyz.webflutter.kadefootballlanguage.BuildConfig.BASE_URL

object ApiServices {
    fun getNextMatch(leagueId: String?): String{
        return Uri.parse(BASE_URL).buildUpon()
            .appendPath(API_KEY)
            .appendPath("eventsnextleague.php")
            .appendQueryParameter("id", leagueId)
            .build().toString()
    }

    fun getLastMatch(leagueId: String? = "4328"): String{
        return Uri.parse(BASE_URL).buildUpon()
            .appendPath(API_KEY)
            .appendPath("eventspastleague.php")
            .appendQueryParameter("id", leagueId)
            .build().toString()
    }

    fun getDetailLeague(leagueId: String?): String{
        return Uri.parse(BASE_URL).buildUpon()
            .appendPath(API_KEY)
            .appendPath("lookupleague.php")
            .appendQueryParameter("id", leagueId)
            .build().toString()
    }

    fun getDetailMatch(eventId: String?): String{
        return Uri.parse(BASE_URL).buildUpon()
            .appendPath(API_KEY)
            .appendPath("lookupevent.php")
            .appendQueryParameter("id", eventId)
            .build().toString()
    }

    fun getTeamBadge(idTeam: String?): String{
        return Uri.parse(BASE_URL).buildUpon()
            .appendPath(API_KEY)
            .appendPath("lookupteam.php")
            .appendQueryParameter("id", idTeam)
            .build().toString()
    }

    fun getSearchMatch(query: String?): String{
        return Uri.parse(BASE_URL).buildUpon()
            .appendPath(API_KEY)
            .appendPath("searchevents.php")
            .appendQueryParameter("e", query)
            .build().toString()
    }

    fun getAllTeams(idTeam: String?): String{
        return Uri.parse(BASE_URL).buildUpon()
            .appendPath(API_KEY)
            .appendPath("lookup_all_teams.php")
            .appendQueryParameter("id", idTeam)
            .build().toString()
    }
}