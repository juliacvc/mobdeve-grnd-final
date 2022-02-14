package com.mobdeve.s11.mco2.deleon.coronel.grnd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.database.*
import com.mobdeve.s11.mco2.deleon.coronel.grnd.databinding.ActivityRegisterBinding
import com.mobdeve.s11.mco2.deleon.coronel.grnd.models.User

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.registerBtn.setOnClickListener {
            var goToMainActivity = Intent(applicationContext, MainActivity::class.java)

            if(binding.firstname.text.isEmpty() ||
                binding.lastname.text.isEmpty() ||
                binding.gender.text.isEmpty() ||
                binding.birthday.text.isEmpty() ||
                binding.contactnumber.text.isEmpty() ||
                binding.password.text.isEmpty() ||
                binding.confassword.text.isEmpty()) {
                Toast.makeText(applicationContext, "Incomplete user details.", Toast.LENGTH_LONG).show()
            }
            else {
                val firstname = binding.firstname.text.toString()
                val lastname = binding.lastname.text.toString()
                val gender = binding.gender.text.toString()
                val birthday = binding.birthday.text.toString()
                val contactnumber = binding.contactnumber.text.toString()
                val email = binding.emailaddress.text.toString()
                val password = binding.password.text.toString()

                database = FirebaseDatabase.getInstance().getReference("Users")
                val user = User(firstname, lastname, gender, birthday, contactnumber, email, password)

                database.child(firstname).setValue(user).addOnSuccessListener {
                    //Clear text fields
//                    binding.firstname.text.clear()
//                    binding.lastname.text.clear()
//                    binding.gender.text.clear()
//                    binding.birthday.text.clear()
//                    binding.contactnumber.text.clear()
//                    binding.password.text.clear()
//                    binding.confassword.text.clear()

                    Toast.makeText(applicationContext, "Success", Toast.LENGTH_LONG).show()

                    startActivity(goToMainActivity)
                    finish()
                }
            }
        }
    }
}