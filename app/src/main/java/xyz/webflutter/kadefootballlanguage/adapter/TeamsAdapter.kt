package xyz.webflutter.kadefootballlanguage.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_teams.view.*
import xyz.webflutter.kadefootballlanguage.R
import xyz.webflutter.kadefootballlanguage.model.ModelTeams

class TeamsAdapter(private val modelTeams: List<ModelTeams>, private val listener: (ModelTeams)-> Unit): RecyclerView.Adapter<TeamsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamsViewHolder {
        return TeamsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_teams, parent, false))
    }

    override fun getItemCount(): Int = modelTeams.size

    override fun onBindViewHolder(holder: TeamsViewHolder, position: Int) {
        holder.bindItem(modelTeams[position], listener)
    }
}

class TeamsViewHolder(view: View): RecyclerView.ViewHolder(view) {
    fun bindItem(modelTeams: ModelTeams, listener: (ModelTeams) -> Unit){
        Picasso.get().load(modelTeams.strTeamLogo).into(itemView.str_team_logo)
        itemView.str_team.text = modelTeams.strTeam
        itemView.str_team_short.text = modelTeams.strTeamShort
        itemView.setOnClickListener { listener(modelTeams) }
    }
}
