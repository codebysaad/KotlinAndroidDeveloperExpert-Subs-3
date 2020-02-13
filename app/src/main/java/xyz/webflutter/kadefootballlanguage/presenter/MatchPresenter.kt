package xyz.webflutter.kadefootballlanguage.presenter

import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import xyz.webflutter.kadefootballlanguage.model.MatchResponses
import xyz.webflutter.kadefootballlanguage.model.TeamResponse
import xyz.webflutter.kadefootballlanguage.utils.rest.ApiRepository
import xyz.webflutter.kadefootballlanguage.utils.rest.ApiServices
import xyz.webflutter.kadefootballlanguage.view.DetailView

class MatchPresenter(private val matchView: DetailView,private val apiRepository: ApiRepository, private val gson: Gson) {

    fun getDetailMatch(idEvents: String){
        matchView.showLoading()
        doAsync {
        val detail = gson.fromJson(apiRepository.doRequest(ApiServices.getDetailMatch(idEvents)),MatchResponses::class.java)
            uiThread {
                matchView.hideLoading()
                matchView.showDetailMatch(detail.matches)
            }
        }
    }

    fun getShowTeamBadgeHome(idTeams: String){
        matchView.showLoading()
        doAsync {
            val badge = gson.fromJson(apiRepository.doRequest(ApiServices.getTeamBadge(idTeams)), TeamResponse::class.java)
            uiThread {
                matchView.hideLoading()
                matchView.showTeamBadgeHome(badge.bdageTeam)
            }
        }
    }

    fun getShowTeamBadgeAway(idTeams: String){
        matchView.showLoading()
        doAsync {
            val badge = gson.fromJson(apiRepository.doRequest(ApiServices.getTeamBadge(idTeams)), TeamResponse::class.java)
            uiThread {
                matchView.hideLoading()
                matchView.showTeamBadgeAway(badge.bdageTeam)
            }
        }
    }
}