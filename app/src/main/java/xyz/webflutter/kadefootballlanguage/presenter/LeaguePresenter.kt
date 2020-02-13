package xyz.webflutter.kadefootballlanguage.presenter

import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import xyz.webflutter.kadefootballlanguage.model.LeagueResponse
import xyz.webflutter.kadefootballlanguage.utils.rest.ApiRepository
import xyz.webflutter.kadefootballlanguage.utils.rest.ApiServices
import xyz.webflutter.kadefootballlanguage.view.LeagueView

class LeaguePresenter (private val leagueView: LeagueView, private val apiRepository: ApiRepository, private val gson: Gson) {
    fun getDetailLeague(idLeague: String){
        leagueView.showLoading()
        doAsync {
            val detail = gson.fromJson(apiRepository.doRequest(ApiServices.getDetailLeague(idLeague)), LeagueResponse::class.java)
            uiThread {
                leagueView.hideLoading()
                leagueView.showDetailLeague(detail.detailLeague)
            }
        }
    }
}