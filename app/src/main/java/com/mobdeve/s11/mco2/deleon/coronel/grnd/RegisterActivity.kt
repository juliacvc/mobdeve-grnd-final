package com.mobdeve.s11.mco2.deleon.coronel.grnd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.mobdeve.s11.mco2.deleon.coronel.grnd.databinding.ActivityRegisterBinding

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerBtn.setOnClickListener{
            var goToMainActivity = Intent(applicationContext, MainActivity::class.java)

            if(binding.firstname.text.isEmpty() &&
                binding.lastname.text.isEmpty() &&
                binding.gender.text.isEmpty() &&
                binding.birthday.text.isEmpty() &&
                binding.contactnumber.text.isEmpty() &&
                binding.password.text.isEmpty() &&
                binding.confassword.text.isEmpty()) {
                Toast.makeText(applicationContext, "Incomplete user details.", Toast.LENGTH_LONG).show()
            }
            else {
                startActivity(goToMainActivity)
                finish()
            }
        }
    }
}