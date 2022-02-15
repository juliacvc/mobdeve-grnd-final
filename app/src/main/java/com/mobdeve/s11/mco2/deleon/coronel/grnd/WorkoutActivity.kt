package com.mobdeve.s11.mco2.deleon.coronel.grnd

import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.storage.FirebaseStorage
import com.mobdeve.s11.mco2.deleon.coronel.grnd.databinding.ActivityWorkoutBinding
import java.io.File

class WorkoutActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWorkoutBinding

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
    }
}