package xyz.webflutter.kadefootballlanguage.presenter

import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import xyz.webflutter.kadefootballlanguage.model.TeamResponse
import xyz.webflutter.kadefootballlanguage.utils.rest.ApiRepository
import xyz.webflutter.kadefootballlanguage.utils.rest.ApiServices
import xyz.webflutter.kadefootballlanguage.view.TeamsView

class TeamsPresenter(
    private val teamsView: TeamsView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {

    fun getListTeams(idLeague: String) {
        teamsView.showLoading()
        doAsync {
            val list = gson.fromJson(
                apiRepository.doRequest(ApiServices.getAllTeams(idLeague)),
                TeamResponse::class.java
            )
            uiThread {
                teamsView.hideLoading()
                teamsView.showListTeam(list.bdageTeam)
            }
        }
    }

    fun getDetailTeam(idTeam: String){
        teamsView.showLoading()
        doAsync {
            val details = gson.fromJson(apiRepository.doRequest(ApiServices.getTeamBadge(idTeam)),
                TeamResponse::class.java)
            uiThread {
                teamsView.hideLoading()
                teamsView.showListTeam(details.bdageTeam)
            }
        }
    }
}