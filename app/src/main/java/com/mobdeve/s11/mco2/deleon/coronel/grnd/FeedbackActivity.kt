package com.mobdeve.s11.mco2.deleon.coronel.grnd

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.mobdeve.s11.mco2.deleon.coronel.grnd.databinding.ActivityFeedbackBinding
import com.mobdeve.s11.mco2.deleon.coronel.grnd.databinding.ActivityLoginBinding

class FeedbackActivity: AppCompatActivity() {
    private lateinit var binding: ActivityFeedbackBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var firebase: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityFeedbackBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.returnBtn.setOnClickListener{
            var goToMainActivity = Intent(applicationContext, MainActivity::class.java)
            startActivity(goToMainActivity)
            finish()
        }

        binding.feedbackBtn.setOnClickListener{
            var goToMainActivity = Intent(applicationContext, MainActivity::class.java)

            if(binding.feedback.text.isEmpty()){
                Toast.makeText(applicationContext, "Cannot send empty feedback", Toast.LENGTH_LONG).show()
            }
            else{
                val feedback = binding.feedback.text.toString()

                Toast.makeText(applicationContext, "Feedback sent!", Toast.LENGTH_LONG).show()
                startActivity(goToMainActivity)
                finish()
            }

        }
    }

}