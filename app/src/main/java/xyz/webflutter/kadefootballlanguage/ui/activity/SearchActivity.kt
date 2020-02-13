package xyz.webflutter.kadefootballlanguage.ui.activity

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_search.*
import xyz.webflutter.kadefootballlanguage.R
import xyz.webflutter.kadefootballlanguage.adapter.MatchAdapter
import xyz.webflutter.kadefootballlanguage.model.ModelMatch
import xyz.webflutter.kadefootballlanguage.presenter.MainPresenter
import xyz.webflutter.kadefootballlanguage.utils.SOCCER_RESPONSE
import xyz.webflutter.kadefootballlanguage.utils.invisible
import xyz.webflutter.kadefootballlanguage.utils.rest.ApiRepository
import xyz.webflutter.kadefootballlanguage.utils.visible
import xyz.webflutter.kadefootballlanguage.view.MainView

class SearchActivity : AppCompatActivity(), MainView,(ModelMatch) -> Unit {

    private var searchMatch: MutableList<ModelMatch> = mutableListOf()
    private lateinit var presenter: MainPresenter
    private lateinit var adapter: MatchAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)
        rv_search.layoutManager = LinearLayoutManager(this)
        adapter = MatchAdapter(searchMatch, this)
        rv_search.adapter = adapter

        val apiRepository = ApiRepository()
        val gson = Gson()
        presenter = MainPresenter(this, apiRepository, gson)
        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                query.let {
                    presenter.getSearch(it)
                }
                hideSoftKeyboard(this@SearchActivity)
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    fun hideSoftKeyboard(activity: Activity) {
        val inputMethodManager = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(
            activity.currentFocus!!.windowToken, 0
        )
    }

    override fun showLoading() {
        progress_bar_search.visible()
    }

    override fun hideLoading() {
        progress_bar_search.invisible()
    }

    override fun showMatchList(data: List<ModelMatch>) {
        val dataFiltered = data.filter { it.strSport == SOCCER_RESPONSE }
        if (dataFiltered.isNullOrEmpty()){
            val toast = Toast.makeText(this,R.string.error_search, Toast.LENGTH_LONG)
            toast.show()
        }else{
            searchMatch.clear()
            searchMatch.addAll(dataFiltered)
            adapter.notifyDataSetChanged()
        }
        hideLoading()
    }

    override fun invoke(models: ModelMatch) {
        val intent = Intent(this, DetailMatchActivity::class.java)
        intent.putExtra(DetailMatchActivity.EXTRA_ID_EVENT, models.idEvent)
        intent.putExtra(DetailMatchActivity.EXTRA_ID_HOME_TEAM, models.idHomeTeam)
        intent.putExtra(DetailMatchActivity.EXTRA_ID_AWAY_TEAM, models.idAwayTeam)
        intent.putExtra(DetailMatchActivity.EXTRA_STR_EVENT, models.strEvent)
        startActivity(intent)
    }
}
