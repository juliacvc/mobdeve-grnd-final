package com.mobdeve.s11.mco2.deleon.coronel.grnd

import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import com.google.firebase.storage.FirebaseStorage
import com.mobdeve.s11.mco2.deleon.coronel.grnd.databinding.ActivityWorkoutBinding
import java.io.File

class WorkoutActivity : YouTubeBaseActivity() {
    private lateinit var binding: ActivityWorkoutBinding

    val YOUTUBE_API = "AIzaSyAI_hATIvMcy-kJXQTdJAccysyDuThJm0w"

    private lateinit var youtubePlayer: YouTubePlayerView
    private lateinit var btnStart: Button

    lateinit var youtubePlayerInit: YouTubePlayer.OnInitializedListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var bundle = intent.extras
        var name = bundle!!.getString("name")
        var image = bundle!!.getString("image")
        var category = bundle!!.getString("category")
        var level = bundle!!.getString("level")
        var description = bundle!!.getString("description")
        var duration = bundle!!.getInt("duration")
        var equipment = bundle!!.getString("equipment")
        var youtubeLink = bundle!!.getString("youtubeLink")

        youtubePlayer = findViewById(R.id.youtubePlayer)
        btnStart = findViewById(R.id.btnStart)

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

        binding.workoutTitle.text = name
        val storageRef = FirebaseStorage.getInstance().reference.child("Workouts/${image}.jpeg")
        val localfile = File.createTempFile("tempImage", "jpeg")
        storageRef.getFile(localfile).addOnSuccessListener {
            val bitmap = BitmapFactory.decodeFile(localfile.absolutePath)
            binding.workoutImage.setImageBitmap(bitmap)
        }
        binding.workoutType.text = "$category, $level"
        binding.descriptionText.text = description
        binding.durationText.text = "$duration minutes"
        binding.equipmentText.text = equipment

        btnStart.setOnClickListener{
            youtubePlayer.initialize(YOUTUBE_API, youtubePlayerInit)
        }
    }
}