package xyz.webflutter.kadefootballlanguage.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import xyz.webflutter.kadefootballlanguage.ui.fragment.match.last.LastMatchFragment
import xyz.webflutter.kadefootballlanguage.ui.fragment.match.next.NextMatchFragment

class MainFragmentAdapter(fm: FragmentManager): FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position){
            0 -> {
                LastMatchFragment()
            }
            else -> {
                NextMatchFragment()
            }
        }
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Last Match"
            else -> {return "Next Match"}
        }
    }
}