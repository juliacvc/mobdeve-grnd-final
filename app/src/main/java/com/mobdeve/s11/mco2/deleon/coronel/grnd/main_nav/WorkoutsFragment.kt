package com.mobdeve.s11.mco2.deleon.coronel.grnd.main_nav

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.mobdeve.s11.mco2.deleon.coronel.grnd.adapters.FragmentAdapter
import com.mobdeve.s11.mco2.deleon.coronel.grnd.databinding.FragmentWorkoutsBinding
import com.mobdeve.s11.mco2.deleon.coronel.grnd.workouts_nav.WorkoutListFragment
import com.mobdeve.s11.mco2.deleon.coronel.grnd.workouts_nav.WorkoutPlansFragment

class WorkoutsFragment : Fragment() {

    private lateinit var binding: FragmentWorkoutsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWorkoutsBinding.inflate(inflater, container, false)
        val view = binding.root

        var viewPager = binding.viewPager
        var tabLayout = binding.tabLayout

        val fragmentAdapter = FragmentAdapter(childFragmentManager)
        fragmentAdapter.addFragment(WorkoutListFragment(), "Workouts")
        fragmentAdapter.addFragment(WorkoutPlansFragment(), "Workout Plans")

        viewPager.adapter = fragmentAdapter
        tabLayout.setupWithViewPager(viewPager)

        return view
    }
}