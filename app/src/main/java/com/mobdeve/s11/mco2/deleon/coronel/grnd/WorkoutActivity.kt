package com.mobdeve.s11.mco2.deleon.coronel.grnd

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.mobdeve.s11.mco2.deleon.coronel.grnd.databinding.ActivityWorkoutBinding
import com.mobdeve.s11.mco2.deleon.coronel.grnd.models.NewsModel
import com.mobdeve.s11.mco2.deleon.coronel.grnd.models.WorkoutModel

class WorkoutActivity : YouTubeBaseActivity() {
    private lateinit var binding: ActivityWorkoutBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var firebase: FirebaseDatabase
    val YOUTUBE_API = "AIzaSyAI_hATIvMcy-kJXQTdJAccysyDuThJm0w"
    private lateinit var youtubePlayer: YouTubePlayerView
    private lateinit var btnStart: Button
    lateinit var youtubePlayerInit: YouTubePlayer.OnInitializedListener
    private var workoutsList = ArrayList<WorkoutModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Declare database instance
        auth = FirebaseAuth.getInstance()
        val currentUser = auth.currentUser
        firebase = FirebaseDatabase.getInstance()
        database = FirebaseDatabase.getInstance().getReference("Users")

        loadWorkouts(currentUser)

        var bundle = intent.extras
        var name = bundle!!.getString("name")
        var image = bundle!!.getString("image")
        var category = bundle!!.getString("category")
        var level = bundle!!.getString("level")
        var description = bundle!!.getString("description")
        var duration = bundle!!.getInt("duration")
        var equipment = bundle!!.getString("equipment")
        var youtubeLink = bundle!!.getString("youtubeLink")

        youtubePlay(youtubeLink)

        binding.workoutTitle.text = name

        binding.workoutType.text = "$category, $level"
        binding.descriptionText.text = description
        binding.durationText.text = "$duration minutes"
        binding.equipmentText.text = equipment

        btnStart.setOnClickListener{
            youtubePlayer.initialize(YOUTUBE_API, youtubePlayerInit)

            workoutsList.add(WorkoutModel(name!!, image!!, category!!, level!!, description!!, duration, equipment!!, youtubeLink!!))

            database.child(currentUser?.uid!!).child("Workouts").setValue(workoutsList)
        }
    }

    private fun youtubePlay(youtubeLink: String?) {
        youtubePlayer = binding.youtubePlayer
        btnStart = binding.btnStart

        youtubePlayerInit = object : YouTubePlayer.OnInitializedListener{
            override fun onInitializationSuccess(
                p0: YouTubePlayer.Provider?,
                p1: YouTubePlayer?,
                p2: Boolean
            ) {
                p1?.loadVideo(youtubeLink)
            }


            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                Toast.makeText(applicationContext, "Video unable to load", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun loadWorkouts(currentUser: FirebaseUser?) {
        database.child(currentUser?.uid!!).child("Workouts").addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
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
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}