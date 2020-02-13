package xyz.webflutter.kadefootballlanguage.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import xyz.webflutter.kadefootballlanguage.R
import xyz.webflutter.kadefootballlanguage.model.ModelMatch
import kotlinx.android.synthetic.main.item_next_match.view.*
import xyz.webflutter.kadefootballlanguage.utils.convertDate

class MatchAdapter(private val modelMatches: List<ModelMatch>, private val listener: (ModelMatch) -> Unit): RecyclerView.Adapter<MatchAdapter.ViewHolder>(){

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        fun bindItem(modelMatch: ModelMatch, listener: (ModelMatch) -> Unit ){
            itemView.date_next_match.text = convertDate(modelMatch.dateEvent?:"", modelMatch.strTime?:"")
            itemView.str_team_home.text = modelMatch.strHomeTeam
            itemView.str_team_away.text = modelMatch.strAwayTeam
            itemView.score_team_home.text = modelMatch.intHomeScore
            itemView.score_team_away.text = modelMatch.intAwayScore
            itemView.setOnClickListener { listener(modelMatch) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchAdapter.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_next_match, parent, false))
    }

    override fun getItemCount(): Int = modelMatches.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(modelMatches[position], listener)
    }
}