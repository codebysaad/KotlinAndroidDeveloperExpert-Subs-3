package xyz.webflutter.kadefootballlanguage.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import xyz.webflutter.kadefootballlanguage.ui.fragment.favorite.MatchFavoriteFragment
import xyz.webflutter.kadefootballlanguage.ui.fragment.favorite.TeamsFavoriteFragment

class TabFavoriteFragment(fragmentManager: FragmentManager): FragmentPagerAdapter(fragmentManager) {
    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> {MatchFavoriteFragment()}
            else -> {TeamsFavoriteFragment()}
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Match Favorite"
            else -> {return "Teams Favorite"}
        }
    }
}