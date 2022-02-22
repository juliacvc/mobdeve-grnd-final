package com.mobdeve.s11.mco2.deleon.coronel.grnd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.mobdeve.s11.mco2.deleon.coronel.grnd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    private lateinit var database: DatabaseReference
    private lateinit var firebase: FirebaseDatabase
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        auth = FirebaseAuth.getInstance()
        database = FirebaseDatabase.getInstance().getReference("Users")
        firebase = FirebaseDatabase.getInstance()

        loadHeaderInfo()
        navigation()
    }

    private fun loadHeaderInfo(){
        var headerView : View = binding.navView.getHeaderView(0)
        var userName : TextView = headerView.findViewById(R.id.user_name)
        var email : TextView = headerView.findViewById(R.id.email)

        //Get currentuser details
        val user = auth.currentUser
        val dbref = database.child(user?.uid!!)

        email.text = user.email

        //Get realtime database information of user
        dbref.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                userName.text = "${snapshot.child("firstname").value.toString()} ${snapshot.child("lastname").value.toString()}"
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }

    private fun navigation() {
        val drawerlayout : DrawerLayout = binding.drawerlayout
        val navview : NavigationView = binding.navView

        binding.profileBtn.setOnClickListener{
            drawerlayout.openDrawer(GravityCompat.START)
        }

        navview.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_profile -> {
                    var gotoProfileActivity = Intent(applicationContext, ProfileActivity::class.java)
                    startActivity(gotoProfileActivity)
                }
                R.id.nav_settings -> Toast.makeText(
                    applicationContext,
                    "clicked Settings",
                    Toast.LENGTH_SHORT
                ).show()
                R.id.nav_logout -> {
                    auth.signOut()
                    var goToLoginActivity = Intent(applicationContext, LoginActivity::class.java)
                    startActivity(goToLoginActivity)
                    finish()
                }
                R.id.nav_feedback -> {
                    var goToFeedbackActivity = Intent(applicationContext, FeedbackActivity::class.java)
                    startActivity(goToFeedbackActivity)
                    finish()
                }
            }

            true
        }

        val bottomNavigationView = binding.navbarMain
        val navController = findNavController(R.id.fragment)

        bottomNavigationView.setupWithNavController(navController)

    }

}