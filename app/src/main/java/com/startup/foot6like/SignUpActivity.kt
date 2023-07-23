@file:Suppress("DEPRECATION")

package com.startup.foot6like

import android.app.ProgressDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import com.google.firebase.auth.FirebaseAuth
import com.startup.foot6like.databinding.ActivitySignUpBinding

//ViewBinding
private lateinit var binding: ActivitySignUpBinding

//ProcessDialog
private lateinit var progressDialog: ProgressDialog

//FirebaseAuth
private lateinit var firebaseAuth: FirebaseAuth

//Email & Password
private var email = ""
private var password = ""

class SignUpActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //configure progress dialog
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please wait")
        progressDialog.setMessage("Creating account...")
        progressDialog.setCanceledOnTouchOutside(false)

        //init firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()

        //handle, click, begin login
        binding.signUpBtn.setOnClickListener {
            validateData()
        }

        // handle cklock, open register activity
        binding.haveAccountTv.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    LoginActivity::class.java
                )
            )
        }

    }

    private fun validateData() {
        //get data
        email = binding.emailEt.text.toString().trim()
        password = binding.passwordEt.text.toString().trim()

        //validate data
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            //invalid email format
            binding.emailEt.error = "Invalid email format"
        } else if (TextUtils.isEmpty(password)) {
            binding.passwordEt.error = "Please enter password"
        } else if (password.length < 5 ) {
            binding.passwordEt.error = "Password is less than 5 letters"
        } else {
            firebaseSignUp()
        }
    }

    private fun firebaseSignUp() {
        //show progress
        progressDialog.show()

        //create account
        firebaseAuth.createUserWithEmailAndPassword(email, password)
            .addOnSuccessListener { authResult ->
                // Sign up success
                progressDialog.dismiss()

                // Get current user info from authResult
                val firebaseUser = authResult?.user
                var userEmail = firebaseUser?.email

                if (userEmail != null) {
                    Toast.makeText(this, "Account created with email as $userEmail", Toast.LENGTH_SHORT).show()

                    // Open profile
                    startActivity(Intent(this, ProfileActivity::class.java))
                    finish()
                } else {
                    // Handle the case when user email is null
                    Toast.makeText(this, "Account creation failed. Please try again.", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener { exception ->
                // Sign up failed
                progressDialog.dismiss()
                Toast.makeText(this, "Sign Up Failed due to ${exception.message}", Toast.LENGTH_SHORT).show()
            }
    }
}