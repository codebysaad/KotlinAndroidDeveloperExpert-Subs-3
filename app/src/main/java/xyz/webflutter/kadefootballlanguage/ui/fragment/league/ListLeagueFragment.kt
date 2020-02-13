package xyz.webflutter.kadefootballlanguage.ui.fragment.league

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_league.*
import xyz.webflutter.kadefootballlanguage.R
import xyz.webflutter.kadefootballlanguage.adapter.AdapterFootball
import xyz.webflutter.kadefootballlanguage.model.ItemFootball
import xyz.webflutter.kadefootballlanguage.ui.activity.DetailLeagueActivity

class ListLeagueFragment : Fragment() {
    private val itemFootball: MutableList<ItemFootball> = mutableListOf()
    private lateinit var adapterFootball: AdapterFootball
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_league, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        rv_list_league.layoutManager = GridLayoutManager(context, 2)
        adapterFootball = AdapterFootball(itemFootball){
            val toast = Toast.makeText(context, it.name, Toast.LENGTH_SHORT)
            toast.show()
            val intent = Intent(context, DetailLeagueActivity::class.java)
            intent.putExtra(DetailLeagueActivity.EXTRA_ID, it)
            startActivity(intent)
        }
        rv_list_league.adapter = adapterFootball
        initData()
    }

    private fun initData() {
        val id = resources.getStringArray(R.array.id)
        val name = resources.getStringArray(R.array.name)
        val desc = resources.getStringArray(R.array.desc)
        val logo = resources.obtainTypedArray(R.array.logo)

        itemFootball.clear()
        for (i in name.indices) {
            itemFootball.add(
                ItemFootball(
                    id[i],
                    name[i],
                    desc[i],
                    logo.getResourceId(i, 0)
                )
            )
        }
        logo.recycle()
    }
}