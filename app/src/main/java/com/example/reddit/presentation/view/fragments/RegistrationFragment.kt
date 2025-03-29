package com.example.reddit.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.reddit.R
import com.example.reddit.data.models.User
import com.example.reddit.data.storage.UserPreferences
import com.example.reddit.data.storage.room.DatabaseProvider
import com.example.reddit.data.storage.room.UserDao
import com.example.reddit.databinding.FragmentRegistrationBinding
import com.example.reddit.presentation.actions.RegistrationFragmentActions
import com.example.reddit.presentation.view_models.HomeFragmentViewModel
import com.example.reddit.presentation.view_models.RegistrationFragmentViewModel
import com.example.reddit.presentation.view_models.SignInFragmentViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegistrationFragment : Fragment() {

    private var viewModel : RegistrationFragmentViewModel ?= null

    private var _binding : FragmentRegistrationBinding ?= null
    private val binding : FragmentRegistrationBinding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application).create(
            RegistrationFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegistrationBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userPrefs = UserPreferences(requireContext())
        val database = DatabaseProvider.getDatabase(requireContext())
        val dao = database.userDao()

        initClickListeners(userPrefs, dao)

        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel?.liveData?.observe(viewLifecycleOwner) { state ->
            state?.let {
                if (it.toMainScreenBtn){
                    toNextScreen(HomeFragment()) //, "MainFragment"
                }
                if (it.toSignInScreenBtn){
                    toNextScreen(SignInFragment()) //, "SignInFragment"
                }
            }
        }
    }

    private fun toNextScreen(fragment : Fragment){
        if (fragment == HomeFragment()){

        }
        else{
            findNavController().navigate(R.id.action_registrationFragment_to_signInFragment)
        }
//        parentFragmentManager.beginTransaction()
//            .replace(R.id.fragmentContainerView, fragment, fragmentTag)
//            .commit()
    }


    private fun initClickListeners(userPrefs : UserPreferences, dao: UserDao){
        _binding?.arLogInBtn?.setOnClickListener {
            if (_binding?.arUsernameEt?.text.toString().isEmpty() || _binding?.arPasswordEt?.text.toString().isEmpty()) {
                Toast.makeText(requireContext(), "You have not filled in the fields for entry!", Toast.LENGTH_SHORT).show()
            } else {
                lifecycleScope.launch {
                    if (dao.getUserByUsername(_binding?.arUsernameEt?.text.toString()) == null){
                        val job = launch(Dispatchers.IO) {
                            viewModel?.handleAction(RegistrationFragmentActions.RegisterUser(_binding?.arUsernameEt?.text.toString(),  _binding?.arPasswordEt?.text.toString(), dao))
                        }
                        job.join() // Дожидаемся завершения вставки
                        val user = getUserFromDb(_binding?.arUsernameEt?.text.toString(), dao)
                        if (user != null) {
                            userPrefs.saveUser(user)
                            findNavController().navigate(R.id.action_registrationFragment_to_homeFragment)
//                            viewModel.handleAction(RegistrationFragmentActions.GoToMainScreen(_binding?.arUsernameEt?.text.toString()))
                        }
                    }
                    else {
                        Toast.makeText(requireContext(), "The user with this login already exists. Think of a different login.", Toast.LENGTH_SHORT).show()
                    }
                }

            }

        }
        _binding?.arReturnBtn?.setOnClickListener {
            viewModel?.handleAction(RegistrationFragmentActions.GoToSignInScreen)
        }
    }

    private suspend fun getUserFromDb(login: String, dao: UserDao) :  User? {
        return withContext(Dispatchers.IO) {
            dao.getUserByUsername(login) // Получаем пользователя по логину
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}