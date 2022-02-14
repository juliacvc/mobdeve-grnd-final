package com.mobdeve.s11.mco2.deleon.coronel.grnd.workouts_nav

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobdeve.s11.mco2.deleon.coronel.grnd.adapters.CategoryAdapter
import com.mobdeve.s11.mco2.deleon.coronel.grnd.dao.CategoryDAO
import com.mobdeve.s11.mco2.deleon.coronel.grnd.dao.CategoryDaoArrayList
import com.mobdeve.s11.mco2.deleon.coronel.grnd.databinding.FragmentWorkoutListBinding
import com.mobdeve.s11.mco2.deleon.coronel.grnd.models.CategoryModel

class WorkoutListFragment : Fragment() {
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var binding: FragmentWorkoutListBinding
    var workoutList = ArrayList<CategoryModel?>()
    var workoutDAO: CategoryDAO = CategoryDaoArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWorkoutListBinding.inflate(inflater, container, false)
        val view = binding.root

        populateList()

        categoryAdapter = CategoryAdapter(requireActivity(), workoutList) { clickedItem ->
            val dialog = CustomDialogFragment(clickedItem.name)
            dialog.show(requireActivity().supportFragmentManager, "customDialog")
        }

        binding.workoutListView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.workoutListView.adapter = categoryAdapter

        return view
    }

    fun populateList() {
        workoutList = workoutDAO.getWorkouts()!!
    }
}