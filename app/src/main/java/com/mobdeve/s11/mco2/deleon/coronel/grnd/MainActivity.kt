package com.mobdeve.s11.mco2.deleon.coronel.grnd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.mobdeve.s11.mco2.deleon.coronel.grnd.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private lateinit var database: DatabaseReference
    var firebase: FirebaseDatabase?  =null
    lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance().getReference("Users")
        firebase = FirebaseDatabase.getInstance()

        val drawerlayout : DrawerLayout = binding.drawerlayout
        val navview : NavigationView = binding.navView

        loadProfile()


        binding.profileBtn.setOnClickListener{
            drawerlayout.openDrawer(GravityCompat.START)
        }

        navview.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_profile -> Toast.makeText(
                    applicationContext,
                    "Clicked Profile",
                    Toast.LENGTH_SHORT
                ).show()
                R.id.nav_settings -> Toast.makeText(
                    applicationContext,
                    "clicked Settings",
                    Toast.LENGTH_SHORT
                ).show()
                R.id.nav_logout -> {
                    var goToLoginActivity = Intent(applicationContext, LoginActivity::class.java)

                    startActivity(goToLoginActivity)
                    finish()
                }
            }

            true
        }

        val bottomNavigationView = binding.navbarMain
        val navController = findNavController(R.id.fragment)

        bottomNavigationView.setupWithNavController(navController)
    }

    private fun loadProfile(){
        val user = auth.currentUser
    }
}