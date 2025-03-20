package com.example.reddit.presentation.view.activities

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.reddit.R
import com.example.reddit.databinding.ActivityMainBinding
import com.example.reddit.databinding.FragmentHomeBinding
import com.example.reddit.presentation.view.fragments.SplashFragment

class MainActivity : AppCompatActivity() {

//    private var _binding: ActivityMainBinding?= null
//    private val binding: ActivityMainBinding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

//    override fun onResume() {
//        super.onResume()
//        findViewById<View>(R.id.nav_host_fragment_container)?.layoutParams =
//            ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
//
//    }
}

//        if(savedInstanceState == null){
//            supportFragmentManager
//                .beginTransaction()
//                .add(R.id.nav_host_fragment_container, SplashFragment(), "SplashFragment")
//                .commit()
//        }