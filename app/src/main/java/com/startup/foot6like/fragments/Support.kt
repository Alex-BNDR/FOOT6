package com.startup.foot6like.fragments


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.startup.foot6like.enterapp.LoginActivity
import com.startup.foot6like.databinding.FragmentSupportBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
//private const val ARG_PARAM1 = "param1"
//private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Support.newInstance] factory method to
 * create an instance of this fragment.
 */
class Support : Fragment() {

    private lateinit var binding: FragmentSupportBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSupportBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firebaseAuth = FirebaseAuth.getInstance()
        checkUser()

        binding.logoutBtn2.setOnClickListener {
            firebaseAuth.signOut()
            checkUser()
        }
    }

    private fun checkUser() {
        val firebaseUser = firebaseAuth.currentUser
        if (firebaseUser != null) {
            val email = firebaseUser.email
            binding.emailTv2.text = email
        } else {
            // If user is null, navigate back to LoginActivity
            startActivity(Intent(activity, LoginActivity::class.java))
            activity?.finish()
        }
    }
}

//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_support, container, false)
//    }
//
//    //ViewBinding
//    private lateinit var binding: FragmentSupportBinding
//
//    //FirebaseAuth
//    private lateinit var firebaseAuth: FirebaseAuth
//
//    //Email & Password
//    private var email = ""
//    private var password = ""
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = FragmentSupportBinding.inflate(layoutInflater)
//
//        //init firebaseAuth
//        firebaseAuth = FirebaseAuth.getInstance()
//        checkUser()
//
//        //handle, click, logout
//        binding.logoutBtn2.setOnClickListener {
//            firebaseAuth.signOut()
//            checkUser()
//        }
//
//    }
//
//    private fun checkUser() {
//        // if user is already logged in --> get current user
//
//        val firebaseUser = firebaseAuth.currentUser
//        if (firebaseUser != null) {
//            //user is not null --> logged in, get user info
//            val email = firebaseUser.email
//            //set to text view
//            binding.emailTv2.text = email
//        } else {
//
//            //user is null --> not logged in, go to login activity
//            startActivity(Intent(activity, LoginActivity::class.java))
//        }
//
//    }






//    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        arguments?.let {
//            param1 = it.getString(ARG_PARAM1)
//            param2 = it.getString(ARG_PARAM2)
//        }
//    }
//
//    companion object {
//        /**
//         * Use this factory method to create a new instance of
//         * this fragment using the provided parameters.
//         *
//         * @param param1 Parameter 1.
//         * @param param2 Parameter 2.
//         * @return A new instance of fragment Support.
//         */
//        // TODO: Rename and change types and number of parameters
//        @JvmStatic
//        fun newInstance(param1: String, param2: String) =
//            Support().apply {
//                arguments = Bundle().apply {
//                    putString(ARG_PARAM1, param1)
//                    putString(ARG_PARAM2, param2)
//                }
//            }
//    }
