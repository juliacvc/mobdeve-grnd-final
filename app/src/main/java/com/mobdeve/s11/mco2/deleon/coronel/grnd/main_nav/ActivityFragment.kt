package com.mobdeve.s11.mco2.deleon.coronel.grnd.main_nav

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mobdeve.s11.mco2.deleon.coronel.grnd.activity_nav.ActivityListFragment
import com.mobdeve.s11.mco2.deleon.coronel.grnd.activity_nav.BadgesFragment
import com.mobdeve.s11.mco2.deleon.coronel.grnd.adapters.FragmentAdapter
import com.mobdeve.s11.mco2.deleon.coronel.grnd.databinding.FragmentActivityBinding

class ActivityFragment : Fragment() {

    private lateinit var binding: FragmentActivityBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentActivityBinding.inflate(inflater, container, false)
        val view = binding.root

        var viewPager = binding.viewPager
        var tabLayout = binding.tabLayout

        val fragmentAdapter = FragmentAdapter(childFragmentManager)
        fragmentAdapter.addFragment(ActivityListFragment(), "Activity")
        fragmentAdapter.addFragment(BadgesFragment(), "Badges")

        viewPager.adapter = fragmentAdapter
        tabLayout.setupWithViewPager(viewPager)

        return view
    }

}