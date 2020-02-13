package xyz.webflutter.kadefootballlanguage.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_league.*
import xyz.webflutter.kadefootballlanguage.R
import xyz.webflutter.kadefootballlanguage.model.ItemFootball
import xyz.webflutter.kadefootballlanguage.model.ModelLeague
import xyz.webflutter.kadefootballlanguage.model.ModelTeams
import xyz.webflutter.kadefootballlanguage.presenter.LeaguePresenter
import xyz.webflutter.kadefootballlanguage.utils.invisible
import xyz.webflutter.kadefootballlanguage.utils.rest.ApiRepository
import xyz.webflutter.kadefootballlanguage.utils.visible
import xyz.webflutter.kadefootballlanguage.view.LeagueView

class DetailLeagueActivity : AppCompatActivity(), LeagueView, (ModelLeague) -> Unit {
    companion object {
        const val EXTRA_ID = "EXTRA_ID"
    }

    private var details: MutableList<ModelLeague> = mutableListOf()
    private lateinit var presenter: LeaguePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_league)
        val data = intent.getParcelableExtra<ItemFootball>(EXTRA_ID)
        val request = ApiRepository()
        val gson = Gson()
        presenter = LeaguePresenter(this, request, gson)
        data.let {
            initialActionBar(it)
            it?.logo?.let { it1 ->
                Picasso.get()
                    .load(it1)
                    .placeholder(R.drawable.ic_broken_image)
                    .error(R.drawable.ic_broken_image)
                    .into(league_badge)
            }
            presenter.getDetailLeague(it?.id.toString())
        }
    }

    private fun initialActionBar(itemFootball: ItemFootball?) {
        setSupportActionBar(toolbar_detail)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.apply {
            title = itemFootball?.name
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }
        collaps_toolbar.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.white))
        collaps_toolbar.setExpandedTitleColor(ContextCompat.getColor(this, R.color.white))
        collaps_toolbar.title = itemFootball?.name
    }

    override fun showLoading() {
        progress_bar_detail.visible()
    }

    override fun hideLoading() {
        progress_bar_detail.invisible()
    }

    override fun showDetailLeague(data: List<ModelLeague>) {
        details.clear()
        details.addAll(data)

        str_alternate.text = details[0].strLeagueAlternate
        int_formed_year.text = details[0].intFormedYear
        date.text = details[0].dateFirstEvent
        str_gender.text = details[0].strGender
        str_country.text = details[0].strCountry
        str_facebook.text = details[0].strFacebook
        str_twitter.text = details[0].strTwitter
        str_youtube.text = details[0].strYoutube
        str_website.text = details[0].strWebsite
        str_description.text = details[0].strDescriptionEN
    }

    override fun invoke(models: ModelLeague) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
