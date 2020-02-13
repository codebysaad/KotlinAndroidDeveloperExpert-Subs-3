package xyz.webflutter.kadefootballlanguage.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import xyz.webflutter.kadefootballlanguage.ui.fragment.league.ListLeagueFragment
import xyz.webflutter.kadefootballlanguage.ui.fragment.league.TeamsFragment

class ListFragmentAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position){
            0 -> {
                ListLeagueFragment()
            }
            else -> {
                TeamsFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "List League"
            else -> {return "List Teams"}
        }
    }
}