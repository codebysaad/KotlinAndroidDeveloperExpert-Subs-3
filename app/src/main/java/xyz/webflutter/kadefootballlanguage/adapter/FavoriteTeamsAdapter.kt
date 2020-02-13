package xyz.webflutter.kadefootballlanguage.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_teams.view.*
import xyz.webflutter.kadefootballlanguage.R
import xyz.webflutter.kadefootballlanguage.db.TeamsDatabase

class FavoriteTeamsAdapter(
    private val modelTeams: List<TeamsDatabase>,
    private val listener: (TeamsDatabase) -> Unit
) : RecyclerView.Adapter<FavoriteTeamsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteTeamsViewHolder {
        return FavoriteTeamsViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_teams,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = modelTeams.size

    override fun onBindViewHolder(holder: FavoriteTeamsViewHolder, position: Int) {
        holder.bindItem(modelTeams[position], listener)
    }
}

class FavoriteTeamsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bindItem(modelTeams: TeamsDatabase, listener: (TeamsDatabase) -> Unit) {
        Picasso.get().load(modelTeams.strTeamLogo).into(itemView.str_team_logo)
        itemView.str_team.text = modelTeams.strTeam
        itemView.str_team_short.text = modelTeams.strTeamShort
        itemView.setOnClickListener { listener(modelTeams) }
    }
}