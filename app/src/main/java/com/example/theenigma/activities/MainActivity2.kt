package com.example.theenigma.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import android.os.Build
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.theenigma.R
import com.google.android.material.navigation.NavigationView


class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        FullScreencall()
        window.setNavigationBarColor(getResources().getColor(R.color.black))

//        val navView = findViewById<NavigationView>(R.id.navView)
//        val drawer = findViewById<DrawerLayout>(R.id.drawer_layout)
//
//        val toggle = ActionBarDrawerToggle(this,drawer,R.string.open,R.string.close)
//        drawer.addDrawerListener(toggle)
//        toggle.syncState()
//
//        val navDrawerbutton = findViewById<ImageView>(R.id.NavdawerButton)
//
//        navDrawerbutton.setOnClickListener {
//            drawer.openDrawer(GravityCompat.START);
//        }


        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment2) as NavHostFragment
        val navController = navHostFragment.navController

        NavigationUI.setupWithNavController(bottomNavigationView,navController)


    }



    fun FullScreencall() {
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            val v: View = this.window.decorView
            v.setSystemUiVisibility(View.GONE)
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            val decorView: View = window.decorView
            val uiOptions: Int =
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
            decorView.setSystemUiVisibility(uiOptions)
        }
    }
}