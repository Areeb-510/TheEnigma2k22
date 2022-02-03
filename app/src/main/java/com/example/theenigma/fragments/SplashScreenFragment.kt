package com.example.theenigma.fragments

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.fragment.findNavController
import com.example.theenigma.activities.MainActivity2
import com.example.theenigma.R
import com.google.firebase.auth.FirebaseAuth


class SplashScreenFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }

        val mp = MediaPlayer.create(activity, R.raw.intro)
        mp.start()
    }

    private lateinit var splashanim: Animation
    private lateinit var splashimage: ImageView
    private lateinit var auth: FirebaseAuth


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_splash_screen, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val constraint_layout = view.findViewById<ConstraintLayout>(R.id.main_layout)

        val animationDrawable: AnimationDrawable = constraint_layout.background as AnimationDrawable
//        animationDrawable.setEnterFadeDuration(5000)
        animationDrawable.setExitFadeDuration(1000)
        animationDrawable.start()

        auth = FirebaseAuth.getInstance()






        splashanim = AnimationUtils.loadAnimation(activity, R.anim.splash_screen_animation)
        splashimage = view.findViewById(R.id.splash_image_exe)

        splashimage.animation = splashanim
        var check = 0;
        if (auth.currentUser != null) {
            check++

        }


        Handler().postDelayed({
            if (check > 0) {
                val intent = Intent(context, MainActivity2::class.java).apply {
                }
                startActivity(intent)
                activity?.finish()
            } else {
                // null
                val action =
                    SplashScreenFragmentDirections.actionSplashScreenFragmentToSignInFragment()
                findNavController().navigate(action)
            }
        }, 5000)

    }


}