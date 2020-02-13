package xyz.webflutter.kadefootballlanguage.view

import xyz.webflutter.kadefootballlanguage.model.ModelTeams

interface TeamsView {
    fun showLoading()
    fun hideLoading()
    fun showListTeam(data: List<ModelTeams>)
}