package com.mobdeve.s11.mco2.deleon.coronel.grnd.main_nav

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobdeve.s11.mco2.deleon.coronel.grnd.R
import com.mobdeve.s11.mco2.deleon.coronel.grnd.adapters.PlansAdapter
import com.mobdeve.s11.mco2.deleon.coronel.grnd.adapters.WorkoutAdapter
import com.mobdeve.s11.mco2.deleon.coronel.grnd.dao.PlansDAO
import com.mobdeve.s11.mco2.deleon.coronel.grnd.dao.PlansDaoArrayList
import com.mobdeve.s11.mco2.deleon.coronel.grnd.dao.WorkoutDAO
import com.mobdeve.s11.mco2.deleon.coronel.grnd.dao.WorkoutDaoArrayList
import com.mobdeve.s11.mco2.deleon.coronel.grnd.databinding.FragmentCustomDialogBinding
import com.mobdeve.s11.mco2.deleon.coronel.grnd.databinding.FragmentHomeBinding
import com.mobdeve.s11.mco2.deleon.coronel.grnd.models.WorkoutModel
import com.mobdeve.s11.mco2.deleon.coronel.grnd.models.WorkoutPlanModel

class CustomDialogFragment(title: String): DialogFragment() {
    private lateinit var binding: FragmentCustomDialogBinding
    var workoutsList = ArrayList<WorkoutModel?>()
    var workoutDAO: WorkoutDAO = WorkoutDaoArrayList()
    var categoryTitle = title

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCustomDialogBinding.inflate(inflater, container, false)
        val view = binding.root

        workoutsList = workoutDAO.getWorkouts()!!

        var workoutAdapter = WorkoutAdapter(workoutsList)
        binding.workoutCategory.text = "$categoryTitle WORKOUTS"
        binding.workoutsList.layoutManager = GridLayoutManager(activity, 2)
        binding.workoutsList.adapter = workoutAdapter

        return view
    }


}