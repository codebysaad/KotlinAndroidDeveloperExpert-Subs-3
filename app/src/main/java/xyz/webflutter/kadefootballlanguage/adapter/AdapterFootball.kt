package xyz.webflutter.kadefootballlanguage.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_football.view.*
import xyz.webflutter.kadefootballlanguage.model.ItemFootball
import xyz.webflutter.kadefootballlanguage.R

class AdapterFootball(
    private val itemFootball: List<ItemFootball>,
    private val listener: (ItemFootball) -> Unit) :
    RecyclerView.Adapter<AdapterFootball.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_football,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(itemFootball[position], listener)
    }

    override fun getItemCount(): Int = itemFootball.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItem(itemFootball: ItemFootball, listener: (ItemFootball) -> Unit) {
            itemView.nameLeague.text = itemFootball.name
            itemFootball.logo.let { Picasso.get().load(it).fit().into(itemView.logo) }
            itemView.setOnClickListener { listener(itemFootball) }
        }
    }
}