package com.mobdeve.s11.mco2.deleon.coronel.grnd

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.mobdeve.s11.mco2.deleon.coronel.grnd.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {
    private lateinit var binding: ActivityProfileBinding
    private lateinit var database: DatabaseReference
    private lateinit var firebase: FirebaseDatabase
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().getReference("Users")
        firebase = FirebaseDatabase.getInstance()

        val currentUser = auth.currentUser
        val dbref = database.child(currentUser?.uid!!)

        dbref.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                binding.firstname.hint = snapshot.child("firstname").value.toString()
                binding.lastname.hint = snapshot.child("lastname").value.toString()
                binding.gender.hint = snapshot.child("gender").value.toString()
                binding.birthday.hint = snapshot.child("birthday").value.toString()
                binding.contactnumber.hint = snapshot.child("contactnumber").value.toString()
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })

        updateUser()
    }

    private fun updateUser() {
        val currentUser = auth.currentUser

        //Update User
        binding.updateBtn.setOnClickListener{
            if(binding.firstname.text.isEmpty() ||
                binding.lastname.text.isEmpty() ||
                binding.gender.text.isEmpty() ||
                binding.birthday.text.isEmpty() ||
                binding.contactnumber.text.isEmpty()) {
                Toast.makeText(applicationContext, "Incomplete user details.", Toast.LENGTH_LONG).show()
            } else {
                val firstname = binding.firstname.text.toString()
                val lastname = binding.lastname.text.toString()
                val gender = binding.gender.text.toString()
                val birthday = binding.birthday.text.toString()
                val contactnumber = binding.contactnumber.text.toString()

                val user = mapOf<String, String>(
                    "firstname" to firstname,
                    "lastname" to lastname,
                    "gender" to gender,
                    "birthday" to birthday,
                    "contactnumber" to contactnumber
                )

                database.child(currentUser.uid).updateChildren(user).addOnSuccessListener {
                    binding.firstname.text.clear()
                    binding.lastname.text.clear()
                    binding.gender.text.clear()
                    binding.birthday.text.clear()
                    binding.contactnumber.text.clear()

                    Toast.makeText(
                        applicationContext,
                        "Successfully updated profile!",
                        Toast.LENGTH_LONG
                    ).show()
                }.addOnFailureListener {
                    Toast.makeText(
                        applicationContext,
                        "Failed to update profile!",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}