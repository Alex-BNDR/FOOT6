package com.startup.foot6like.enterapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import com.startup.foot6like.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    //ViewBinding
    private lateinit var binding: ActivityProfileBinding

    //FirebaseAuth
    private lateinit var firebaseAuth: FirebaseAuth

    //Email & Password
    private var email = ""
    private var password = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //init firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        //handle, click, logout
        binding.logoutBtn.setOnClickListener {
            firebaseAuth.signOut()
            checkUser()
        }

    }

    private fun checkUser() {
        // if user is already logged in --> get current user

        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null) {
            //user is not null --> logged in, get user info
            val email = firebaseUser.email
            //set to text view
            binding.emailTv.text = email
        } else {
            //user is null --> not logged in, go to login activity
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

    }
}