package xyz.webflutter.kadefootballlanguage.ui.fragment.league

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_teams.*
import org.jetbrains.anko.support.v4.onRefresh

import xyz.webflutter.kadefootballlanguage.R
import xyz.webflutter.kadefootballlanguage.adapter.SpinnerAdapters
import xyz.webflutter.kadefootballlanguage.adapter.TeamsAdapter
import xyz.webflutter.kadefootballlanguage.model.ModelLeague
import xyz.webflutter.kadefootballlanguage.model.ModelTeams
import xyz.webflutter.kadefootballlanguage.presenter.LeaguePresenter
import xyz.webflutter.kadefootballlanguage.presenter.TeamsPresenter
import xyz.webflutter.kadefootballlanguage.ui.activity.DetailTeamActivity
import xyz.webflutter.kadefootballlanguage.utils.invisible
import xyz.webflutter.kadefootballlanguage.utils.rest.ApiRepository
import xyz.webflutter.kadefootballlanguage.utils.visible
import xyz.webflutter.kadefootballlanguage.view.LeagueView
import xyz.webflutter.kadefootballlanguage.view.TeamsView

/**
 * A simple [Fragment] subclass.
 */
class TeamsFragment : Fragment(), TeamsView, (ModelTeams)->Unit{

    private lateinit var idLeague: String
    private var teams: MutableList<ModelTeams> = mutableListOf()
    private lateinit var presenter: TeamsPresenter
    private lateinit var adapter: TeamsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_teams, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val teamList = resources.getStringArray(R.array.name)
        val idList = resources.getStringArray(R.array.id)
        rv_teams.layoutManager = LinearLayoutManager(context)
        adapter = TeamsAdapter(teams, this)
        rv_teams.adapter = adapter

        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamsPresenter(this, request, gson)

        val adapterSinners = SpinnerAdapters(requireContext().applicationContext, teamList, idList)
        spinner_teams.adapter = adapterSinners
        spinner_teams.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                idLeague = adapterSinners.listIdTeam[position]
                presenter.getListTeams(idLeague)
            }
        }

        swipe_teams.onRefresh {
            presenter.getListTeams(idLeague)
        }
    }

    override fun showLoading() {
        progress_bar_teams.visible()
    }

    override fun hideLoading() {
        progress_bar_teams.invisible()
    }

    override fun showListTeam(data: List<ModelTeams>) {
        swipe_teams.isRefreshing = false
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
        hideLoading()
    }

    override fun invoke(models: ModelTeams) {
        val intent = Intent(context, DetailTeamActivity::class.java)
        intent.putExtra(DetailTeamActivity.EXTRA_ID, models.idTeam)
        intent.putExtra(DetailTeamActivity.EXTRA_STR_TEAM, models.strTeam)
        startActivity(intent)
    }
}