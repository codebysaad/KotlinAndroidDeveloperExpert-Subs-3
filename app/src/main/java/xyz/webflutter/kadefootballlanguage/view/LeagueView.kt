package xyz.webflutter.kadefootballlanguage.view

import xyz.webflutter.kadefootballlanguage.model.ModelLeague
import xyz.webflutter.kadefootballlanguage.model.ModelTeams

interface LeagueView {
    fun showLoading()
    fun hideLoading()
    fun showDetailLeague(data: List<ModelLeague>)
}