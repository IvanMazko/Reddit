package com.example.reddit.presentation.view.activities

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.reddit.R
import com.example.reddit.databinding.ActivityMainBinding
import com.example.reddit.databinding.FragmentHomeBinding
import com.example.reddit.presentation.view.fragments.SplashFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    private var _binding: ActivityMainBinding?= null
    val binding: ActivityMainBinding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Правильная инициализация binding
        _binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)


        // Получаем NavController безопасно
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as? NavHostFragment
        val navController = navHostFragment?.navController


        if (navController != null) {
            binding.bottomNavigation.setupWithNavController(navController)
        } else {
            Log.e("MainActivity", "NavController is null!")
        }

    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
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