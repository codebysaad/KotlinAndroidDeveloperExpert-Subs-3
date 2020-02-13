package xyz.webflutter.kadefootballlanguage.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*
import xyz.webflutter.kadefootballlanguage.db.FavoriteDatabase.Companion.DATE_EVENT
import xyz.webflutter.kadefootballlanguage.db.FavoriteDatabase.Companion.DESCRIPTION_EN
import xyz.webflutter.kadefootballlanguage.db.FavoriteDatabase.Companion.ID
import xyz.webflutter.kadefootballlanguage.db.FavoriteDatabase.Companion.ID_AWAY_TEAM
import xyz.webflutter.kadefootballlanguage.db.FavoriteDatabase.Companion.ID_EVENT
import xyz.webflutter.kadefootballlanguage.db.FavoriteDatabase.Companion.ID_HOME_TEAM
import xyz.webflutter.kadefootballlanguage.db.FavoriteDatabase.Companion.INT_HOME_SCORE
import xyz.webflutter.kadefootballlanguage.db.FavoriteDatabase.Companion.INT_AWAY_SCORE
import xyz.webflutter.kadefootballlanguage.db.FavoriteDatabase.Companion.INT_AWAY_SHOOTS
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

class MyDatabaseOpenHelper(context: Context) :
    ManagedSQLiteOpenHelper(context, "MatchFavorite.db", null, 8) {
    companion object {
        private var INSTANCE: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(context: Context): MyDatabaseOpenHelper {
            if (INSTANCE == null) {
                INSTANCE = MyDatabaseOpenHelper(context.applicationContext)
            }
            return INSTANCE as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.createTable(
            TABLE_FAVORITE, true,
            ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            STR_EVENT to TEXT,
            ID_EVENT to TEXT,
            DATE_EVENT to TEXT,
            ID_AWAY_TEAM to TEXT,
            INT_AWAY_SCORE to TEXT,
            INT_AWAY_SHOOTS to TEXT,
            STR_AWAY_FORMATION to TEXT,
            STR_AWAY_DEFENSE to TEXT,
            STR_AWAY_FORWARD to TEXT,
            STR_AWAY_GOAL_KEEPER to TEXT,
            STR_AWAY_MID to TEXT,
            STR_AWAY_SUBTITUTES to TEXT,
            STR_AWAY_RED to TEXT,
            STR_AWAY_TEAM to TEXT,
            STR_AWAY_YELLOW to TEXT,
            ID_HOME_TEAM to TEXT,
            INT_HOME_SCORE to TEXT,
            INT_HOME_SHOOTS to TEXT,
            STR_HOME_FORMATION to TEXT,
            STR_HOME_DEFENSE to TEXT,
            STR_HOME_FORWARD to TEXT,
            STR_HOME_GOAL_KEEPER to TEXT,
            STR_HOME_MID to TEXT,
            STR_HOME_SUBTITUTES to TEXT,
            STR_HOME_RED to TEXT,
            STR_HOME_TEAM to TEXT,
            STR_HOME_YELLOW to TEXT,
            TIME_EVENT to TEXT,
            STR_SPORT to TEXT,
            STR_DATE to TEXT,
            DESCRIPTION_EN to TEXT
        )
        db.createTable(
            TeamsDatabase.TABLE_TEAMS, true,
            TeamsDatabase.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            TeamsDatabase.ID_TEAM to TEXT,
            TeamsDatabase.STR_TEAM to TEXT,
            TeamsDatabase.STR_TEAM_SHORT to TEXT,
            TeamsDatabase.STR_ALTERNATE to TEXT,
            TeamsDatabase.INT_FORMED_YEAR to TEXT,
            TeamsDatabase.STR_LEAGUE to TEXT,
            TeamsDatabase.ID_LEAGUE to TEXT,
            TeamsDatabase.STR_MANAGER to TEXT,
            TeamsDatabase.STR_STADIUM to TEXT,
            TeamsDatabase.STR_STADIUM_THUMB to TEXT,
            TeamsDatabase.STR_STADIUM_DESCRIPTION to TEXT,
            TeamsDatabase.STR_STADIUM_LOCATION to TEXT,
            TeamsDatabase.INT_STADIUM_CAPACITY to TEXT,
            TeamsDatabase.STR_DESCRIPTION_EN to TEXT,
            TeamsDatabase.STR_COUNTRY to TEXT,
            TeamsDatabase.STR_TEAM_BADGE to TEXT,
            TeamsDatabase.STR_TEAM_JERSEY to TEXT,
            TeamsDatabase.STR_TEAM_LOGO to TEXT,
            TeamsDatabase.STR_TEAM_BANNER to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.dropTable(TABLE_FAVORITE, true)
        db.dropTable(TeamsDatabase.TABLE_TEAMS, true)
    }
}

val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)