package com.example.theenigma.fragments

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import com.example.theenigma.R
import com.example.theenigma.activities.MainActivity2
import com.example.theenigma.data.User
import com.example.theenigma.data.UserDao
import com.example.theenigma.data.UserStatDao
import com.example.theenigma.data.UserStats
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.ktx.Firebase


class SignInFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
        getActivity()?.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    private lateinit var topanim : Animation
    private lateinit var textanim : Animation
    private lateinit var mapImage : ImageView
    private lateinit var title : TextView
    private lateinit var google_sign_in : CardView
    private lateinit var googleSignInClient :GoogleSignInClient
    private lateinit var auth: FirebaseAuth

    private var RC_SIGN_IN = 123

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        topanim = AnimationUtils.loadAnimation(activity, R.anim.top_animation)
        textanim = AnimationUtils.loadAnimation(activity, R.anim.text_animation)

        google_sign_in = view.findViewById<CardView>(R.id.google_sign_in)
        title = view.findViewById(R.id.title)
        mapImage = view.findViewById<ImageView>(R.id.map)


        mapImage.animation = topanim
        title.animation = textanim

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()

        googleSignInClient = activity?.let { GoogleSignIn.getClient(it, gso) }!!

        auth = FirebaseAuth.getInstance()



        google_sign_in.setOnClickListener {

            signIn()

        }


    }

    private fun signIn() {
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                // Google Sign In was successful, authenticate with Firebase
                val account = task.getResult(ApiException::class.java)!!
                Log.d("TAG", "firebaseAuthWithGoogle:" + account.id)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                // Google Sign In failed, update UI appropriately
                Log.w("TAG", "Google sign in failed", e)
            }
        }
    }

    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        activity?.let {
            auth.signInWithCredential(credential)
                .addOnCompleteListener(it) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("TAG", "signInWithCredential:success")
                        val user = auth.currentUser

                        val userDao = UserDao()
                        val leaderboard = UserStatDao()
                        if (user != null) {
                            userDao.addUser(User(user.uid.toString(), user.displayName.toString(), user.photoUrl.toString()))
                            leaderboard.addUser(UserStats(user.displayName.toString(),user.photoUrl.toString(),0,12))
                        }


                        val intent = Intent(context, MainActivity2::class.java).apply {
                        }
                        startActivity(intent)
                        activity?.finish()
                    } else {
                        // If sign in fails, display a message to the user.
                        Log.w("TAG", "signInWithCredential:failure", task.exception)
                        Toast.makeText(activity,"Some Error Occured in Sign In",Toast.LENGTH_SHORT).show()
                    }
                }
        }
    }


}