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
import kotlinx.android.synthetic.main.activity_detail_match.*
import kotlinx.android.synthetic.main.detail_match_layout.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.db.delete
import xyz.webflutter.kadefootballlanguage.R
import xyz.webflutter.kadefootballlanguage.db.FavoriteDatabase
import xyz.webflutter.kadefootballlanguage.db.FavoriteDatabase.Companion.DATE_EVENT
import xyz.webflutter.kadefootballlanguage.db.FavoriteDatabase.Companion.DESCRIPTION_EN
import xyz.webflutter.kadefootballlanguage.db.FavoriteDatabase.Companion.ID_AWAY_TEAM
import xyz.webflutter.kadefootballlanguage.db.FavoriteDatabase.Companion.ID_EVENT
import xyz.webflutter.kadefootballlanguage.db.FavoriteDatabase.Companion.ID_HOME_TEAM
import xyz.webflutter.kadefootballlanguage.db.FavoriteDatabase.Companion.INT_AWAY_SCORE
import xyz.webflutter.kadefootballlanguage.db.FavoriteDatabase.Companion.INT_AWAY_SHOOTS
import xyz.webflutter.kadefootballlanguage.db.FavoriteDatabase.Companion.INT_HOME_SCORE
import xyz.webflutter.kadefootballlanguage.db.FavoriteDatabase.Companion.INT_HOME_SHOOTS
import xyz.webflutter.kadefootballlanguage.db.FavoriteDatabase.Companion.STR_AWAY_DEFENSE
import xyz.webflutter.kadefootballlanguage.db.FavoriteDatabase.Companion.STR_AWAY_FORMATION
import xyz.webflutter.kadefootballlanguage.db.FavoriteDatabase.Companion.STR_AWAY_FORWARD
import xyz.webflutter.kadefootballlanguage.db.FavoriteDatabase.Companion.STR_AWAY_GOAL_KEEPER
import xyz.webflutter.kadefootballlanguage.db.FavoriteDatabase.Companion.STR_AWAY_MID
import xyz.webflutter.kadefootballlanguage.db.FavoriteDatabase.Companion.STR_AWAY_RED
import xyz.webflutter.kadefootballlanguage.db.FavoriteDatabase.Companion.STR_AWAY_SUBTITUTES
import xyz.webflutter.kadefootballlanguage.db.FavoriteDatabase.Companion.STR_AWAY_TEAM
import xyz.webflutter.kadefootballlanguage.db.FavoriteDatabase.Companion.STR_AWAY_YELLOW
import xyz.webflutter.kadefootballlanguage.db.FavoriteDatabase.Companion.STR_DATE
import xyz.webflutter.kadefootballlanguage.db.FavoriteDatabase.Companion.STR_EVENT
import xyz.webflutter.kadefootballlanguage.db.FavoriteDatabase.Companion.STR_HOME_DEFENSE
import xyz.webflutter.kadefootballlanguage.db.FavoriteDatabase.Companion.STR_HOME_FORMATION
import xyz.webflutter.kadefootballlanguage.db.FavoriteDatabase.Companion.STR_HOME_FORWARD
import xyz.webflutter.kadefootballlanguage.db.FavoriteDatabase.Companion.STR_HOME_GOAL_KEEPER
import xyz.webflutter.kadefootballlanguage.db.FavoriteDatabase.Companion.STR_HOME_MID
import xyz.webflutter.kadefootballlanguage.db.FavoriteDatabase.Companion.STR_HOME_RED
import xyz.webflutter.kadefootballlanguage.db.FavoriteDatabase.Companion.STR_HOME_SUBTITUTES
import xyz.webflutter.kadefootballlanguage.db.FavoriteDatabase.Companion.STR_HOME_TEAM
import xyz.webflutter.kadefootballlanguage.db.FavoriteDatabase.Companion.STR_HOME_YELLOW
import xyz.webflutter.kadefootballlanguage.db.FavoriteDatabase.Companion.STR_SPORT
import xyz.webflutter.kadefootballlanguage.db.FavoriteDatabase.Companion.TABLE_FAVORITE
import xyz.webflutter.kadefootballlanguage.db.FavoriteDatabase.Companion.TIME_EVENT
import xyz.webflutter.kadefootballlanguage.db.database
import xyz.webflutter.kadefootballlanguage.model.ModelMatch
import xyz.webflutter.kadefootballlanguage.model.ModelTeams
import xyz.webflutter.kadefootballlanguage.presenter.MatchPresenter
import xyz.webflutter.kadefootballlanguage.utils.convertDate
import xyz.webflutter.kadefootballlanguage.utils.invisible
import xyz.webflutter.kadefootballlanguage.utils.rest.ApiRepository
import xyz.webflutter.kadefootballlanguage.utils.visible
import xyz.webflutter.kadefootballlanguage.view.DetailView

class DetailMatchActivity : AppCompatActivity(), DetailView {
    companion object {
        const val EXTRA_ID_EVENT = "EVENT_ID"
        const val EXTRA_STR_EVENT = "EVENT_STR"
        const val EXTRA_ID_HOME_TEAM = "HOME_ID"
        const val EXTRA_ID_AWAY_TEAM = "AWAY_ID"
    }

    private var detailMatch: MutableList<ModelMatch> = mutableListOf()
    private var isFavorite: Boolean = false
    private var menuItem: Menu? = null
    private lateinit var presenter: MatchPresenter
    private lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_match)

        id = intent.getStringExtra(EXTRA_ID_EVENT)
        val getName = intent.getStringExtra(EXTRA_STR_EVENT)
        val idHomeTeam = intent.getStringExtra(EXTRA_ID_HOME_TEAM)
        val idAwayTeam = intent.getStringExtra(EXTRA_ID_AWAY_TEAM)

        val request = ApiRepository()
        val gson = Gson()
        presenter = MatchPresenter(this, request, gson)
        supportActionBar?.apply {
            title = getName
            setDisplayShowHomeEnabled(true)
            setDisplayHomeAsUpEnabled(true)
        }
        presenter.getDetailMatch(id)
        presenter.getShowTeamBadgeHome(idHomeTeam!!)
        presenter.getShowTeamBadgeAway(idAwayTeam!!)
        favoriteState()
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

    override fun showTeamBadgeHome(data: List<ModelTeams>) {
        Picasso.get().load(data[0].strTeamBadge).into(badge_home_detail)
        badge_home_detail.visible()
    }

    override fun showTeamBadgeAway(data: List<ModelTeams>) {
        Picasso.get().load(data[0].strTeamBadge).into(badge_away_detail)
        badge_away_detail.visible()
    }

    override fun showLoading() {
        progress_bar_detail_match.visible()
    }

    override fun hideLoading() {
        progress_bar_detail_match.invisible()
    }

    override fun showDetailMatch(data: List<ModelMatch>) {

        detailMatch.clear()
        detailMatch.addAll(data)

        str_match_date_detail.text = convertDate(data[0].dateEvent ?: "", data[0].strTime ?: "")
        str_team_home_detail.text = data[0].strHomeTeam
        str_team_away_detail.text = data[0].strAwayTeam
        str_home_goal_detail.text = data[0].intHomeScore
        str_away_goal_detail.text = data[0].intAwayScore
        str_away_shot_detail.text = data[0].intAwayShots
        str_home_shot_detail.text = data[0].intHomeShots
        str_home_yellow_card.text = data[0].strHomeYellowCards
        str_away_yellow_card.text = data[0].strAwayYellowCards
        str_home_red_card.text = data[0].strHomeRedCards
        str_away_red_card.text = data[0].strAwayRedCards
        str_home_formation.text = data[0].strHomeFormation
        str_away_formation.text = data[0].strAwayFormation
        str_home_keeper.text = data[0].strHomeLineupGoalkeeper
        str_away_keeper.text = data[0].strAwayLineupGoalkeeper
        str_home_deffense.text = data[0].strHomeLineupDefense
        str_away_deffense.text = data[0].strAwayLineupDefense
        str_home_midfield.text = data[0].strHomeLineupMidfield
        str_away_midfield.text = data[0].strAwayLineupMidfield
        str_home_forward.text = data[0].strHomeLineupForward
        str_away_forward.text = data[0].strAwayLineupForward
        str_home_subtitutes.text = data[0].strHomeLineupSubstitutes
        str_away_subtitutes.text = data[0].strAwayLineupSubstitutes
    }

    private fun insertToFavorite() {
        try {
            database.use {
                insert(
                    TABLE_FAVORITE,
                    STR_EVENT to detailMatch[0].strEvent,
                    ID_EVENT to detailMatch[0].idEvent,
                    DATE_EVENT to detailMatch[0].dateEvent,
                    ID_AWAY_TEAM to detailMatch[0].idAwayTeam,
                    INT_AWAY_SCORE to detailMatch[0].intAwayScore,
                    INT_AWAY_SHOOTS to detailMatch[0].intAwayShots,
                    STR_AWAY_FORMATION to detailMatch[0].strAwayFormation,
                    STR_AWAY_DEFENSE to detailMatch[0].strAwayLineupDefense,
                    STR_AWAY_FORWARD to detailMatch[0].strAwayLineupForward,
                    STR_AWAY_GOAL_KEEPER to detailMatch[0].strAwayLineupGoalkeeper,
                    STR_AWAY_MID to detailMatch[0].strAwayLineupMidfield,
                    STR_AWAY_SUBTITUTES to detailMatch[0].strAwayLineupSubstitutes,
                    STR_AWAY_RED to detailMatch[0].strAwayRedCards,
                    STR_AWAY_TEAM to detailMatch[0].strAwayTeam,
                    STR_AWAY_YELLOW to detailMatch[0].strAwayYellowCards,
                    ID_HOME_TEAM to detailMatch[0].idHomeTeam,
                    INT_HOME_SCORE to detailMatch[0].intHomeScore,
                    INT_HOME_SHOOTS to detailMatch[0].intHomeShots,
                    STR_HOME_FORMATION to detailMatch[0].strHomeFormation,
                    STR_HOME_DEFENSE to detailMatch[0].strHomeLineupDefense,
                    STR_HOME_FORWARD to detailMatch[0].strHomeLineupForward,
                    STR_HOME_GOAL_KEEPER to detailMatch[0].strHomeLineupGoalkeeper,
                    STR_HOME_MID to detailMatch[0].strHomeLineupMidfield,
                    STR_HOME_SUBTITUTES to detailMatch[0].strHomeLineupSubstitutes,
                    STR_HOME_RED to detailMatch[0].strHomeRedCards,
                    STR_HOME_TEAM to detailMatch[0].strHomeTeam,
                    STR_HOME_YELLOW to detailMatch[0].strHomeYellowCards,
                    TIME_EVENT to detailMatch[0].strTime,
                    STR_SPORT to detailMatch[0].strSport,
                    STR_DATE to detailMatch[0].strDate,
                    DESCRIPTION_EN to detailMatch[0].strDescriptionEN
                )
            }
            val toast = Toast.makeText(applicationContext, "Add to Favorite", Toast.LENGTH_SHORT)
            toast.show()
        } catch (e: SQLiteConstraintException) {
            val toast = Toast.makeText(applicationContext, e.localizedMessage, Toast.LENGTH_SHORT)
            toast.show()
        }
    }

    private fun removeFavorite() {
        try {
            database.use {
                delete(TABLE_FAVORITE, "(ID_EVENT = {EVENT_ID})",
                    "EVENT_ID" to id)
            }
            val toast = Toast.makeText(applicationContext, "Removed to Favorite", Toast.LENGTH_SHORT)
            toast.show()
        }catch (e: SQLiteConstraintException){
            val toast = Toast.makeText(applicationContext, e.localizedMessage, Toast.LENGTH_SHORT)
            toast.show()
        }
    }

    private fun setFavorite() {
        if (isFavorite)
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_favorite_black)
        else
            menuItem?.getItem(0)?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_unfavorite)
    }

    private fun favoriteState(){
        database.use {
            val result = select(TABLE_FAVORITE)
                .whereArgs("(ID_EVENT = {EVENT_ID})", "EVENT_ID" to id)
            val favorite = result.parseList(classParser<FavoriteDatabase>())
            if (favorite.isNotEmpty()) isFavorite = true
        }
    }
}
