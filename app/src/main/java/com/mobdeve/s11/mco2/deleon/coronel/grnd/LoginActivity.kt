package com.mobdeve.s11.mco2.deleon.coronel.grnd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.mobdeve.s11.mco2.deleon.coronel.grnd.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()

        binding.registerBtn.setOnClickListener{
            var goToRegisterActivity = Intent(applicationContext, RegisterActivity::class.java)

            startActivity(goToRegisterActivity)
        }

        binding.loginBtn.setOnClickListener{
            var goToMainActivity = Intent(applicationContext, MainActivity::class.java)

            if(binding.emailaddress.text.isEmpty() || binding.password.text.isEmpty() ) {
                Toast.makeText(applicationContext, "Incorrect user details. Kindly try again.", Toast.LENGTH_LONG).show()
            }
            else {
                startActivity(goToMainActivity)
                finish()
            }
        }

    }
}