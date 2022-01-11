package com.mobdeve.s11.mco2.deleon.coronel.grnd.workouts_nav

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobdeve.s11.mco2.deleon.coronel.grnd.R
import com.mobdeve.s11.mco2.deleon.coronel.grnd.adapters.CategoryAdapter
import com.mobdeve.s11.mco2.deleon.coronel.grnd.dao.CategoryDAO
import com.mobdeve.s11.mco2.deleon.coronel.grnd.dao.CategoryDaoArrayList
import com.mobdeve.s11.mco2.deleon.coronel.grnd.databinding.FragmentWorkoutListBinding
import com.mobdeve.s11.mco2.deleon.coronel.grnd.databinding.FragmentWorkoutPlansBinding
import com.mobdeve.s11.mco2.deleon.coronel.grnd.models.CategoryModel

class WorkoutPlansFragment : Fragment() {
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var binding: FragmentWorkoutPlansBinding
    var workoutList = ArrayList<CategoryModel?>()
    var workoutDAO: CategoryDAO = CategoryDaoArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWorkoutPlansBinding.inflate(inflater, container, false)
        val view = binding.root

        populateList()

        categoryAdapter = CategoryAdapter(workoutList)

        binding.workoutPlanView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        binding.workoutPlanView.adapter = categoryAdapter

        return view
    }

    fun populateList() {
        workoutList = workoutDAO.getWorkouts()!!
    }
}