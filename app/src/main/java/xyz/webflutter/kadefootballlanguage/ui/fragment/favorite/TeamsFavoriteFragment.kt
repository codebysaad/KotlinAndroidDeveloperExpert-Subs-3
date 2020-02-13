package xyz.webflutter.kadefootballlanguage.ui.fragment.favorite


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.recyclerview.v7.recyclerView
import org.jetbrains.anko.support.v4.onRefresh
import org.jetbrains.anko.support.v4.swipeRefreshLayout

import xyz.webflutter.kadefootballlanguage.adapter.FavoriteMatchAdapter
import xyz.webflutter.kadefootballlanguage.adapter.FavoriteTeamsAdapter
import xyz.webflutter.kadefootballlanguage.adapter.TeamsAdapter
import xyz.webflutter.kadefootballlanguage.db.FavoriteDatabase
import xyz.webflutter.kadefootballlanguage.db.TeamsDatabase
import xyz.webflutter.kadefootballlanguage.db.database
import xyz.webflutter.kadefootballlanguage.ui.activity.DetailMatchActivity
import xyz.webflutter.kadefootballlanguage.ui.activity.DetailTeamActivity

/**
 * A simple [Fragment] subclass.
 */
class TeamsFavoriteFragment : Fragment(), AnkoComponent<Context>, (TeamsDatabase)->Unit {
    private var favorites: MutableList<TeamsDatabase> = mutableListOf()
    private lateinit var adapter: FavoriteTeamsAdapter
    private lateinit var list: RecyclerView
    private lateinit var swipeRefresh: SwipeRefreshLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return createView(AnkoContext.create(requireContext()))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = FavoriteTeamsAdapter(favorites, this)
        list.adapter = adapter
        swipeRefresh.onRefresh { showFavorite() }
    }

    override fun onResume() {
        super.onResume()
        showFavorite()
    }

    override fun createView(ui: AnkoContext<Context>): View = with(ui) {
        linearLayout {
            lparams(matchParent, matchParent)
            topPadding = dip(16)
            leftPadding = dip(16)
            rightPadding = dip(16)
            swipeRefresh = swipeRefreshLayout {
                setColorSchemeResources(
                    android.R.color.holo_blue_light,
                    android.R.color.holo_green_light,
                    android.R.color.holo_orange_light,
                    android.R.color.holo_red_light)
                list = recyclerView {
                    lparams(matchParent, wrapContent)
                    layoutManager = LinearLayoutManager(context)
                }
            }
        }
    }

    private fun showFavorite(){
        favorites.clear()
        context?.database?.use {
            swipeRefresh.isRefreshing = false
            val result = select(TeamsDatabase.TABLE_TEAMS)
            val favorite = result.parseList(classParser<TeamsDatabase>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

    override fun invoke(models: TeamsDatabase) {
        val intent = Intent(context, DetailTeamActivity::class.java)
        intent.putExtra(DetailTeamActivity.EXTRA_ID, models.idTeam)
        intent.putExtra(DetailTeamActivity.EXTRA_STR_TEAM, models.strTeam)
        startActivity(intent)
    }
}