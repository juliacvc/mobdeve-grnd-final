package com.mobdeve.s11.mco2.deleon.coronel.grnd.workouts_nav

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import com.mobdeve.s11.mco2.deleon.coronel.grnd.WorkoutPageFragment
import com.mobdeve.s11.mco2.deleon.coronel.grnd.adapters.WorkoutAdapter
import com.mobdeve.s11.mco2.deleon.coronel.grnd.databinding.FragmentCustomDialogBinding
import com.mobdeve.s11.mco2.deleon.coronel.grnd.models.WorkoutModel

class CustomDialogFragment(title: String): DialogFragment() {
    private lateinit var binding: FragmentCustomDialogBinding
    private lateinit var dbref: DatabaseReference
    private lateinit var workoutsRecyclerView: RecyclerView
    var workoutsList = ArrayList<WorkoutModel?>()
    var categoryTitle = title

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCustomDialogBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.workoutCategory.text = "$categoryTitle WORKOUTS"
        workoutsRecyclerView = binding.workoutsList
        workoutsRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        getWorkoutsData()

//        val fragment = WorkoutPageFragment()
//        val transaction: FragmentTransaction = childFragmentManager.beginTransaction()
//        transaction.replace(R.id.action_container, fragment)
//        transaction.commit()
    }

    private fun getWorkoutsData() {
        dbref = FirebaseDatabase.getInstance().getReference("Workouts")

        dbref.addValueEventListener(object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {
                for (workoutSnapshot in snapshot.children) {
                    val workout = WorkoutModel(workoutSnapshot.child("name").value.toString(),
                                            workoutSnapshot.child("image").value.toString(),
                                            workoutSnapshot.child("category").value.toString(),
                                            workoutSnapshot.child("level").value.toString(),
                                            workoutSnapshot.child("description").value.toString(),
                                            workoutSnapshot.child("duration").value.toString().toInt(),
                                            workoutSnapshot.child("equipment").value.toString(),
                                            workoutSnapshot.child("youtubeLink").value.toString())

                    if (workout.category.equals(categoryTitle, ignoreCase = true))
                        workoutsList.add(workout)
                    workoutsRecyclerView.adapter = WorkoutAdapter(workoutsList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}