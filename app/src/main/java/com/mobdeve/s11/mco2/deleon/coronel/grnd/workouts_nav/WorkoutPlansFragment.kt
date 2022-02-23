package com.mobdeve.s11.mco2.deleon.coronel.grnd.workouts_nav

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.mobdeve.s11.mco2.deleon.coronel.grnd.adapters.WorkoutPlansAdapter
import com.mobdeve.s11.mco2.deleon.coronel.grnd.databinding.FragmentWorkoutPlansBinding
import com.mobdeve.s11.mco2.deleon.coronel.grnd.models.WorkoutPlanModel

class WorkoutPlansFragment : Fragment() {
    private lateinit var binding: FragmentWorkoutPlansBinding
    private lateinit var plansRecyclerView: RecyclerView
    var plansList = ArrayList<WorkoutPlanModel?>()
    private lateinit var database: DatabaseReference
    private lateinit var firebase: FirebaseDatabase

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWorkoutPlansBinding.inflate(inflater, container, false)
        val view = binding.root

        firebase = FirebaseDatabase.getInstance()
        database = FirebaseDatabase.getInstance().getReference("Workout Plans")

        plansRecyclerView = binding.workoutPlanView
        plansRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        loadPlans()

        return view
    }

    private fun loadPlans() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (planSnapshot in snapshot.children) {
                    val plan = WorkoutPlanModel(null,
                                                planSnapshot.child("name").value.toString(),
                                                planSnapshot.child("description").value.toString(),
                                                null)
                    plansList.add(plan)
                    plansRecyclerView.adapter = WorkoutPlansAdapter(requireActivity(), plansList) { currentItem ->
                        val dialog = PlansDialogFragment(currentItem.name!!)
                        dialog.show(requireActivity().supportFragmentManager, "customDialog")
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}