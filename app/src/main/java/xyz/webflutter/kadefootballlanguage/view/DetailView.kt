package xyz.webflutter.kadefootballlanguage.view

import xyz.webflutter.kadefootballlanguage.model.ModelMatch
import xyz.webflutter.kadefootballlanguage.model.ModelTeams

interface DetailView {
    fun showLoading()
    fun hideLoading()
    fun showDetailMatch(data: List<ModelMatch>)
    fun showTeamBadgeHome(data: List<ModelTeams>)
    fun showTeamBadgeAway(data: List<ModelTeams>)
}