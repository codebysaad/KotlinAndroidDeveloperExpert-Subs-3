package xyz.webflutter.kadefootballlanguage.ui.fragment.league


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_main_list.*

import xyz.webflutter.kadefootballlanguage.R
import xyz.webflutter.kadefootballlanguage.adapter.ListFragmentAdapter

/**
 * A simple [Fragment] subclass.
 */
class MainListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view_pager_list.adapter = ListFragmentAdapter(childFragmentManager)
        tab_layout_list.setupWithViewPager(view_pager_list)
    }
}
