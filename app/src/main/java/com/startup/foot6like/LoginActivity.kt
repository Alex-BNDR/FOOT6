package com.startup.foot6like

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDialog
import com.google.firebase.auth.FirebaseAuth
import com.startup.foot6like.databinding.ActivityLoginBinding


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
                    SignUpActivity::class.java
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
        email = binding.emailEt.text.toString().trim()
        password = binding.passwordEt.text.toString().trim()

        //validate data
        if (TextUtils.isEmpty(email)) {
            //invalid email format
            binding.emailEt.error = "Invalid email format"
        } else if (TextUtils.isEmpty(password)) {
            binding.passwordEt.error = "Please enter password"
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

                //get user info
                val firebaseUser = firebaseAuth.currentUser
                if (firebaseUser != null) {
                    val email = firebaseUser.email
                    Toast.makeText(this, "Logged In as $email", Toast.LENGTH_SHORT).show()

                    //open profile
                    startActivity(Intent(this, ProfileActivity::class.java))
                    finish()
                }

                //open profile
                startActivity(Intent(this, ProfileActivity::class.java))
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
            startActivity(Intent(this, ProfileActivity::class.java))
            finish()
        }
    }
}
