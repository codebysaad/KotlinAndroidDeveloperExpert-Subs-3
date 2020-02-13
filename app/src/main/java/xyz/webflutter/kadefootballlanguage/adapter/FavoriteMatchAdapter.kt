package xyz.webflutter.kadefootballlanguage.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_next_match.view.*
import xyz.webflutter.kadefootballlanguage.R
import xyz.webflutter.kadefootballlanguage.db.FavoriteDatabase
import xyz.webflutter.kadefootballlanguage.utils.convertDate

class FavoriteMatchAdapter(
    private val list: List<FavoriteDatabase>,
    private val listener: (FavoriteDatabase) -> Unit
) :
    RecyclerView.Adapter<FavoriteViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_next_match,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindItem(list[position], listener)
    }
}

class FavoriteViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bindItem(modelMatch: FavoriteDatabase, listener: (FavoriteDatabase) -> Unit) {
        itemView.date_next_match.text =
            convertDate(modelMatch.dateEvent ?: "", modelMatch.strTime ?: "")
        itemView.str_team_home.text = modelMatch.strHomeTeam
        itemView.str_team_away.text = modelMatch.strAwayTeam
        itemView.score_team_home.text = modelMatch.intHomeScore
        itemView.score_team_away.text = modelMatch.intAwayScore
        itemView.setOnClickListener { listener(modelMatch) }
    }
}
