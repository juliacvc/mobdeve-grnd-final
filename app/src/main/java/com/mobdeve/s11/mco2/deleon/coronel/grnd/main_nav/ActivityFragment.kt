package com.mobdeve.s11.mco2.deleon.coronel.grnd.main_nav

import android.opengl.Visibility
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.ktx.Firebase
import com.mobdeve.s11.mco2.deleon.coronel.grnd.adapters.ActivityAdapter
import com.mobdeve.s11.mco2.deleon.coronel.grnd.adapters.NewsAdapter
import com.mobdeve.s11.mco2.deleon.coronel.grnd.databinding.FragmentActivityBinding
import com.mobdeve.s11.mco2.deleon.coronel.grnd.databinding.FragmentHomeBinding
import com.mobdeve.s11.mco2.deleon.coronel.grnd.models.NewsModel
import com.mobdeve.s11.mco2.deleon.coronel.grnd.models.WorkoutModel
import com.mobdeve.s11.mco2.deleon.coronel.grnd.models.WorkoutPlanModel

class ActivityFragment : Fragment() {
    private lateinit var binding: FragmentActivityBinding
    private lateinit var database: DatabaseReference
    private lateinit var workoutsRecyclerView: RecyclerView
    var workoutsList = ArrayList<WorkoutModel?>()
    lateinit var auth: FirebaseAuth
    var count = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentActivityBinding.inflate(inflater, container, false)
        val view = binding.root

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().getReference("Users")
        workoutsRecyclerView = binding.workoutsList
        workoutsRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        val user = auth.currentUser
        loadWorkouts(user)

        if (workoutsList.isEmpty())
            binding.workoutText.visibility = View.VISIBLE

        return view
    }

    private fun loadWorkouts(currentUser: FirebaseUser?) {
        database.child(currentUser?.uid!!).child("Workouts").addValueEventListener(object:
            ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var workoutCount = 0

                for (workoutSnapshot in snapshot.children) {
                    val workout = WorkoutModel(
                        workoutSnapshot.child("name").value.toString(),
                        workoutSnapshot.child("image").value.toString(),
                        workoutSnapshot.child("category").value.toString(),
                        workoutSnapshot.child("level").value.toString(),
                        workoutSnapshot.child("description").value.toString(),
                        workoutSnapshot.child("duration").value.toString().toInt(),
                        workoutSnapshot.child("equipment").value.toString(),
                        workoutSnapshot.child("youtubeLink").value.toString())

                    workoutsList.add(workout)
                    workoutCount++
                    workoutsRecyclerView.adapter = ActivityAdapter(workoutsList)
                }

                count = workoutCount
                binding.workoutText.visibility = View.GONE
                binding.workoutsTotal.text = "$count"
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

}