package xyz.webflutter.kadefootballlanguage.presenter

import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import xyz.webflutter.kadefootballlanguage.model.MatchResponses
import xyz.webflutter.kadefootballlanguage.utils.rest.ApiRepository
import xyz.webflutter.kadefootballlanguage.utils.rest.ApiServices
import xyz.webflutter.kadefootballlanguage.view.MainView

class MainPresenter (private val mainView: MainView, private val apiRepository: ApiRepository, private val gson: Gson){
    fun getNextMatch(idLeague: String?){
        mainView.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(ApiServices.getNextMatch(idLeague)), MatchResponses::class.java)
            uiThread {
                mainView.hideLoading()
                mainView.showMatchList(data.matches)
            }
        }
    }

    fun getLastMatch(idLeague: String?){
        mainView.showLoading()
        doAsync {
            val data = gson.fromJson(apiRepository.doRequest(ApiServices.getLastMatch(idLeague)), MatchResponses::class.java)
            uiThread {
                mainView.hideLoading()
                mainView.showMatchList(data.matches)
            }
        }
    }

    fun getSearch(queryId: String?){
        mainView.showLoading()
        doAsync {
            val query = gson.fromJson(apiRepository.doRequest(ApiServices.getSearchMatch(queryId)), MatchResponses::class.java)
            uiThread {
                mainView.hideLoading()
                mainView.showMatchList(query.search)
            }
        }
    }
}