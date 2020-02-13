package xyz.webflutter.kadefootballlanguage.ui.fragment.match.last

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.last_match_fragment.*
import org.jetbrains.anko.support.v4.onRefresh

import xyz.webflutter.kadefootballlanguage.R
import xyz.webflutter.kadefootballlanguage.adapter.MatchAdapter
import xyz.webflutter.kadefootballlanguage.adapter.SpinnerAdapters
import xyz.webflutter.kadefootballlanguage.model.ModelMatch
import xyz.webflutter.kadefootballlanguage.presenter.MainPresenter
import xyz.webflutter.kadefootballlanguage.ui.activity.DetailMatchActivity
import xyz.webflutter.kadefootballlanguage.utils.invisible
import xyz.webflutter.kadefootballlanguage.utils.rest.ApiRepository
import xyz.webflutter.kadefootballlanguage.utils.visible
import xyz.webflutter.kadefootballlanguage.view.MainView

class LastMatchFragment : Fragment(), MainView, (ModelMatch) -> Unit {

    companion object {
        fun newInstance() = LastMatchFragment()
    }

    private lateinit var idLeague: String
    private var teams: MutableList<ModelMatch> = mutableListOf()
    private lateinit var presenter: MainPresenter
    private lateinit var matchAdapter: MatchAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.last_match_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val teamList = resources.getStringArray(R.array.name)
        val idList = resources.getStringArray(R.array.id)
        rv_last_match.layoutManager = LinearLayoutManager(context)
        matchAdapter = MatchAdapter(teams, this)
        rv_last_match.adapter = matchAdapter

        val request= ApiRepository()
        val gson= Gson()
        presenter = MainPresenter(this, request, gson)

        val adapterSpinner = SpinnerAdapters(context!!.applicationContext, teamList, idList)
        spinner_last.adapter = adapterSpinner
        spinner_last.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                idLeague = adapterSpinner.listIdTeam[position]
                presenter.getLastMatch(idLeague)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) { }
        }

        swipe_last.onRefresh {
            presenter.getLastMatch(idLeague)
        }
    }

    override fun showLoading() {
        progress_bar_last.visible()
    }

    override fun hideLoading() {
        progress_bar_last.invisible()
    }

    override fun showMatchList(data: List<ModelMatch>) {
        swipe_last.isRefreshing = false
        teams.clear()
        teams.addAll(data)
        matchAdapter.notifyDataSetChanged()
        hideLoading()
    }

    override fun invoke(models: ModelMatch) {
        val intent = Intent(context, DetailMatchActivity::class.java)
        intent.putExtra(DetailMatchActivity.EXTRA_ID_EVENT, models.idEvent)
        intent.putExtra(DetailMatchActivity.EXTRA_ID_HOME_TEAM, models.idHomeTeam)
        intent.putExtra(DetailMatchActivity.EXTRA_ID_AWAY_TEAM, models.idAwayTeam)
        intent.putExtra(DetailMatchActivity.EXTRA_STR_EVENT, models.strEvent)
        startActivity(intent)
    }

}
