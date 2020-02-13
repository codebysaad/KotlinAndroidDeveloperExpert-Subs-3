package xyz.webflutter.kadefootballlanguage.ui.activity

import android.database.sqlite.SQLiteConstraintException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail_team.*
import kotlinx.android.synthetic.main.activity_detail_team.str_team_logo
import kotlinx.android.synthetic.main.item_teams.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import xyz.webflutter.kadefootballlanguage.R
import xyz.webflutter.kadefootballlanguage.db.TeamsDatabase
import xyz.webflutter.kadefootballlanguage.db.database
import xyz.webflutter.kadefootballlanguage.model.ModelTeams
import xyz.webflutter.kadefootballlanguage.presenter.TeamsPresenter
import xyz.webflutter.kadefootballlanguage.utils.invisible
import xyz.webflutter.kadefootballlanguage.utils.rest.ApiRepository
import xyz.webflutter.kadefootballlanguage.utils.visible
import xyz.webflutter.kadefootballlanguage.view.TeamsView

class DetailTeamActivity : AppCompatActivity(), TeamsView {

    companion object{
        const val EXTRA_ID: String = "TEAM_MODELS"
        const val EXTRA_STR_TEAM: String = "STR_TEAMS"
    }

    private var details: MutableList<ModelTeams> = mutableListOf()
    private lateinit var presenter: TeamsPresenter
    private var isFavorite: Boolean = false
    private var menuItem: Menu? = null
    private lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)
        id = intent.getStringExtra(EXTRA_ID)
        val strTeam = intent.getStringExtra(EXTRA_STR_TEAM)
        val request= ApiRepository()
        val gson = Gson()
        presenter = TeamsPresenter(this, request, gson)
            initialActionBar(strTeam!!.toString())
            presenter.getDetailTeam(id)
        stateFavorite()
    }

    private fun initialActionBar(team: String) {
        setSupportActionBar(toolbar_teams)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.apply {
            title = team
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }
        collaps_toolbar_teams.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.white))
        collaps_toolbar_teams.setExpandedTitleColor(ContextCompat.getColor(this, R.color.white))
        collaps_toolbar_teams.title = team
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.favorite, menu)
        menuItem = menu
        setFavorite()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.favorite -> {
                if (isFavorite) removeFavorite() else insertToFavorite()

                isFavorite = !isFavorite
                setFavorite()

                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun showLoading() {
        progress_bar_detail_teams.visible()
    }

    override fun hideLoading() {
        progress_bar_detail_teams.invisible()
    }

    override fun showListTeam(data: List<ModelTeams>) {
        details.clear()
        details.addAll(data)

        str_teams.text = details[0].strTeam
        int_formed_year_teams.text = details[0].intFormedYear
        str_league.text = details[0].strLeague
        str_manager.text = details[0].strManager
        str_country_teams.text = details[0].strCountry
        Picasso.get()
            .load(details[0].strTeamJersey)
            .placeholder(R.drawable.ic_broken_image)
            .error(R.drawable.ic_broken_image)
            .into(str_team_jersey)
        str_stadium.text = details[0].strStadium
        str_stadium_location.text = details[0].strStadiumLocation
        str_stadium_capacity.text = details[0].intStadiumCapacity
        Picasso.get()
            .load(details[0].strStadiumThumb)
            .placeholder(R.drawable.ic_broken_image)
            .error(R.drawable.ic_broken_image)
            .into(str_stadium_thumb)
        str_stadium_description.text = details[0].strStadiumDescription
        str_description_team.text = details[0].strDescriptionEN
        Picasso.get()
            .load(details[0].strTeamLogo)
            .placeholder(R.drawable.ic_broken_image)
            .error(R.drawable.ic_broken_image)
            .into(str_team_logo)
    }

    private fun setFavorite(){
        if (isFavorite)
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_favorite_black)
        else
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_unfavorite)
    }
    private fun insertToFavorite(){
        try {
            database.use {
                insert(
                    TeamsDatabase.TABLE_TEAMS,
                    TeamsDatabase.ID_TEAM to details[0].idTeam,
                    TeamsDatabase.STR_TEAM to details[0].strTeam,
                    TeamsDatabase.STR_TEAM_SHORT to details[0].strTeamShort,
                    TeamsDatabase.STR_ALTERNATE to details[0].strAlternate,
                    TeamsDatabase.INT_FORMED_YEAR to details[0].intFormedYear,
                    TeamsDatabase.STR_LEAGUE to details[0].strLeague,
                    TeamsDatabase.ID_LEAGUE to details[0].idLeague,
                    TeamsDatabase.STR_MANAGER to details[0].strManager,
                    TeamsDatabase.STR_STADIUM to details[0].strStadium,
                    TeamsDatabase.STR_STADIUM_THUMB to details[0].strStadiumThumb,
                    TeamsDatabase.STR_STADIUM_DESCRIPTION to details[0].strStadiumDescription,
                    TeamsDatabase.STR_STADIUM_LOCATION to details[0].strStadiumLocation,
                    TeamsDatabase.INT_STADIUM_CAPACITY to details[0].intStadiumCapacity,
                    TeamsDatabase.STR_DESCRIPTION_EN to details[0].strDescriptionEN,
                    TeamsDatabase.STR_COUNTRY to details[0].strCountry,
                    TeamsDatabase.STR_TEAM_BADGE to details[0].strTeamBadge,
                    TeamsDatabase.STR_TEAM_JERSEY to details[0].strTeamJersey,
                    TeamsDatabase.STR_TEAM_LOGO to details[0].strTeamLogo,
                    TeamsDatabase.STR_TEAM_BANNER to details[0].strTeamBanner
                )
            }
            val toast = Toast.makeText(applicationContext, "Add to Favorite", Toast.LENGTH_SHORT)
            toast.show()
        } catch (e: SQLiteConstraintException){
            val toast = Toast.makeText(applicationContext, e.localizedMessage, Toast.LENGTH_SHORT)
            toast.show()
        }
    }
    private fun removeFavorite(){
        try {
            database.use {
                delete(TeamsDatabase.TABLE_TEAMS, "(ID_TEAM = {TEAM_MODELS})",
                    "TEAM_MODELS" to id)
            }
            val toast = Toast.makeText(applicationContext, "Removed to Favorite", Toast.LENGTH_SHORT)
            toast.show()
        }catch (e: SQLiteConstraintException){
            val toast = Toast.makeText(applicationContext, e.localizedMessage, Toast.LENGTH_SHORT)
            toast.show()
        }
    }
    private fun stateFavorite(){
        database.use {
            val result = select(TeamsDatabase.TABLE_TEAMS)
                .whereArgs("(ID_TEAM = {TEAM_MODELS})", "TEAM_MODELS" to id)
            val favorite = result.parseList(classParser<TeamsDatabase>())
            if (favorite.isNotEmpty()) isFavorite = true
        }
    }
}
