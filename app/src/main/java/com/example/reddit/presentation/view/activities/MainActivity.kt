package com.example.reddit.presentation.view.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.reddit.R
import com.example.reddit.presentation.view.fragments.SplashFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(savedInstanceState == null){
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragmentContainerView, SplashFragment(), "SplashFragment")
                .commit()
        }
    }
}