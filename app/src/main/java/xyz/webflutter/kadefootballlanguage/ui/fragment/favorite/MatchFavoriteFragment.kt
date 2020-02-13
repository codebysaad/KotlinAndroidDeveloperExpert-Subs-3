package xyz.webflutter.kadefootballlanguage.ui.fragment.favorite


import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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
import xyz.webflutter.kadefootballlanguage.db.FavoriteDatabase
import xyz.webflutter.kadefootballlanguage.db.database
import xyz.webflutter.kadefootballlanguage.ui.activity.DetailMatchActivity

/**
 * A simple [Fragment] subclass.
 */
class MatchFavoriteFragment : Fragment(), AnkoComponent<Context>, (FavoriteDatabase)-> Unit {
    private var favorites: MutableList<FavoriteDatabase> = mutableListOf()
    private lateinit var adapter: FavoriteMatchAdapter
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
        adapter = FavoriteMatchAdapter(favorites, this)
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
            val result = select(FavoriteDatabase.TABLE_FAVORITE)
            val favorite = result.parseList(classParser<FavoriteDatabase>())
            favorites.addAll(favorite)
            adapter.notifyDataSetChanged()
        }
    }

    override fun invoke(models: FavoriteDatabase) {
        val intent = Intent(context, DetailMatchActivity::class.java)
        intent.putExtra(DetailMatchActivity.EXTRA_ID_EVENT, models.idEvent)
        intent.putExtra(DetailMatchActivity.EXTRA_ID_HOME_TEAM, models.idHomeTeam)
        intent.putExtra(DetailMatchActivity.EXTRA_ID_AWAY_TEAM, models.idAwayTeam)
        intent.putExtra(DetailMatchActivity.EXTRA_STR_EVENT, models.strEvent)
        startActivity(intent)
    }
}
