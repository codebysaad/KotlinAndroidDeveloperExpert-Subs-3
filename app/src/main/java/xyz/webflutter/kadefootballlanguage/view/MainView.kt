package xyz.webflutter.kadefootballlanguage.view

import xyz.webflutter.kadefootballlanguage.model.ModelMatch

interface MainView {
    fun showLoading()
    fun hideLoading()
    fun showMatchList(data: List<ModelMatch>)
}