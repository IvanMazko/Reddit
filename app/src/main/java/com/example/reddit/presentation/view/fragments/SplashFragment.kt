package com.example.reddit.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.reddit.R
import com.example.reddit.data.storage.UserPreferences
import com.example.reddit.databinding.FragmentSplashBinding
import com.example.reddit.presentation.view.activities.MainActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {

    private var _binding : FragmentSplashBinding ?= null
    private val binding : FragmentSplashBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSplashBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as MainActivity).binding.bottomNavigation.visibility = View.INVISIBLE

//        view.layoutParams = ViewGroup.LayoutParams(
//            ViewGroup.LayoutParams.MATCH_PARENT,
//            ViewGroup.LayoutParams.MATCH_PARENT)

        val userPrefs = UserPreferences(requireContext())

        lifecycleScope.launch {
            delay(3000)
            if (checkUser(userPrefs)) {
//                parentFragmentManager.beginTransaction()
//                    .replace(R.id.fragmentContainerView, HomeFragment(), "MainFragment")
//                    .commit()
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
            } else {
//                parentFragmentManager.beginTransaction()
//                    .replace(R.id.fragmentContainerView, SignInFragment(), "SignInFragment")
//                    .commit()
                findNavController().navigate(R.id.action_splashFragment_to_signInFragment)
            }
        }

    }

    private fun checkUser(userPrefs: UserPreferences): Boolean {
        return userPrefs.isUserExists()
    }
    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}