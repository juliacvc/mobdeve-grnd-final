package com.mobdeve.s11.mco2.deleon.coronel.grnd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.mobdeve.s11.mco2.deleon.coronel.grnd.databinding.ActivityRegisterBinding
import com.mobdeve.s11.mco2.deleon.coronel.grnd.models.User

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var database: DatabaseReference
    private lateinit var firebase: FirebaseDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        firebase = FirebaseDatabase.getInstance()
        database = FirebaseDatabase.getInstance().getReference("Users")

        register()
    }

    private fun register() {
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

                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener{
                    if(it.isSuccessful) {
                        val currentUser = auth.currentUser

                        val user = User(firstname, lastname, gender, birthday, contactnumber, email, password, null)

                        database.child(currentUser?.uid!!).setValue(user).addOnSuccessListener {

                            Toast.makeText(applicationContext, "Success", Toast.LENGTH_LONG).show()

                            startActivity(goToMainActivity)
                            finish()
                        }
                    } else {
                        Toast.makeText(applicationContext, "Registration failed, please try again!", Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }
    }
}