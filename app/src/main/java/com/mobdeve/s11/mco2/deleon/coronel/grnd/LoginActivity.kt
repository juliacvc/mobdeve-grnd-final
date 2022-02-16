package com.mobdeve.s11.mco2.deleon.coronel.grnd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.mobdeve.s11.mco2.deleon.coronel.grnd.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var firebase: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().getReference("Users")
        firebase = FirebaseDatabase.getInstance()

        binding.registerBtn.setOnClickListener{
            val goToRegisterActivity = Intent(applicationContext, RegisterActivity::class.java)

            startActivity(goToRegisterActivity)
        }

        binding.loginBtn.setOnClickListener{
            val goToMainActivity = Intent(applicationContext, MainActivity::class.java)

            if(binding.emailaddress.text.isEmpty() || binding.password.text.isEmpty() ) {
                Toast.makeText(applicationContext, "Incorrect user details. Kindly try again.", Toast.LENGTH_LONG).show()
            }

            auth.signInWithEmailAndPassword(binding.emailaddress.text.toString(), binding.password.text.toString())
                .addOnCompleteListener {
                    if (it.isSuccessful){
                        startActivity(goToMainActivity)
                        finish()
                    } else {
                        Toast.makeText(applicationContext, "Login details incorrect. Kindly try again.", Toast.LENGTH_LONG).show()
                    }
                }

        }

    }
}