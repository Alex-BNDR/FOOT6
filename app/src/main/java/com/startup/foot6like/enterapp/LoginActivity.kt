package com.startup.foot6like.enterapp

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.startup.foot6like.MainActivity
import com.startup.foot6like.databinding.ActivityLoginBinding
import com.startup.foot6like.models.User


@Suppress("DEPRECATION")
class LoginActivity : AppCompatActivity() {

    //ViewBinding
    private lateinit var binding: ActivityLoginBinding

    //ProcessDialog
    private lateinit var progressDialog: ProgressDialog

    //FirebaseAuth
    private lateinit var firebaseAuth: FirebaseAuth

    //Email & Password
    private var email = ""
    private var password = ""


//    override fun onStart() {
//        super.onStart()
//        firebaseAuth.currentUser?.delete()?.addOnCompleteListener { task ->
//            if (task.isSuccessful) {
//                // Вдале видалення користувача
//            } else {
//                // Помилка видалення або інша логіка
//            }
//        }
//    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //configure progress dialog
        progressDialog = ProgressDialog(this)
        progressDialog.setTitle("Please wait")
        progressDialog.setMessage("Loging In...")
        progressDialog.setCanceledOnTouchOutside(false)

        //init firebaseAuth
        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        // handle cklock, open register activity
        binding.noAccountTv.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    RegisterActivity::class.java
                )
            )
        }

        //handle, click, begin login
        binding.loginBtn.setOnClickListener {
            validateData()
        }

    }

    private fun validateData() {
        //get data
        email = binding.emailField.text.toString().trim()
        password = binding.passwordField.text.toString().trim()

        //validate data
        if (TextUtils.isEmpty(email)) {
            //invalid email format
            binding.emailField.error = "Invalid email format"
        } else if (TextUtils.isEmpty(password)) {
            binding.passwordField.error = "Please enter password"
        } else if (password.length < 5) {
            binding.passwordField.error = "Password is less than 5 letters"
        } else {
            firebaseLogin()
        }

    }

    private fun firebaseLogin() {
        //show progress
        progressDialog.show()
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnSuccessListener {
                //login success
                progressDialog.dismiss()

                val user = User()
                user.email = email
                user.password = password

                //get user info
                val firebaseUser = firebaseAuth.currentUser
                if (firebaseUser != null) {
                    val email = firebaseUser.email
                    Toast.makeText(this, "Logged In as $email", Toast.LENGTH_SHORT).show()

                    //open profile
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }

                //open profile
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
            .addOnFailureListener { which ->
                //login failed
                progressDialog.dismiss()
                Toast.makeText(this, "Login failed due to${which.message}", Toast.LENGTH_SHORT)
                    .show()
            }
    }

    private fun checkUser() {
        // Перевіримо, чи користувач увійшов в систему
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null) {
            // Користувач увійшов, перенаправимо його на сторінку профілю
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}
