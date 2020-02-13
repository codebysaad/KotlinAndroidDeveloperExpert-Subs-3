package xyz.webflutter.kadefootballlanguage.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import xyz.webflutter.kadefootballlanguage.R

class SpinnerAdapters(
    val context: Context,
    private var listTeam: Array<String>,
    var listIdTeam: Array<String>
) : BaseAdapter() {
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val holder: Holder
        if (convertView == null) {
            view = inflater.inflate(R.layout.item_drop_down, parent, false)
            holder = Holder(view)
            view?.tag = holder
        } else {
            view = convertView
            holder = view.tag as Holder
        }
        val params = view.layoutParams
        params.height = 60
        view.layoutParams = params

        holder.nameTeam.text = listTeam[position]
        holder.idTeam.text = listIdTeam[position]

        return view

    }

    override fun getItem(position: Int): Any? {
        return null
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return listTeam.size
    }

    private class Holder(row: View?) {

        val nameTeam: TextView
        val idTeam: TextView

        init {
            this.nameTeam = row?.findViewById(R.id.tv_teams) as TextView
            this.idTeam = row.findViewById(R.id.tv_id_teams) as TextView
        }
    }
}