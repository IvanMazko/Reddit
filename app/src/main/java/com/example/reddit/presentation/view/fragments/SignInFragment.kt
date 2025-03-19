package com.example.reddit.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.reddit.R
import com.example.reddit.data.models.User
import com.example.reddit.data.storage.UserPreferences
import com.example.reddit.data.storage.room.DatabaseProvider
import com.example.reddit.data.storage.room.UserDao
import com.example.reddit.databinding.FragmentSignInBinding
import com.example.reddit.presentation.actions.SignInFragmentActions
import com.example.reddit.presentation.view_models.SignInFragmentViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SignInFragment : Fragment() {

    private val viewModel: SignInFragmentViewModel by viewModels()

    var _binding : FragmentSignInBinding ?= null
    val binding : FragmentSignInBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignInBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val database = DatabaseProvider.getDatabase(requireContext())
        val dao = database.userDao()
        val userPrefs = UserPreferences(requireContext())

        initClickListeners(userPrefs, dao)

        observeViewModel()
    }

    private fun observeViewModel(){
        viewModel.liveData.observe(viewLifecycleOwner) { state ->
            state?.let {
                if (it.toMainScreenBtn) {
                    toNextScreen(HomeFragment(), "MainFragment")
                }
                if (it.toRegisterScreenBtn) {
                    toNextScreen(RegistrationFragment(), "RegistrationFragment")
                }
            }
        }
    }

    private fun toNextScreen(fragment : Fragment, fragmentTag : String){
//        parentFragmentManager.beginTransaction()
//            .replace(R.id.fragmentContainerView, fragment, fragmentTag)
//            .commit()
    }

    private fun initClickListeners(userPrefs : UserPreferences, dao : UserDao){
        _binding?.signInBtn?.setOnClickListener {
            if (_binding?.loginEt?.text.toString().isEmpty() || _binding?.passwordEt?.text.toString().isEmpty()) {
                Toast.makeText(requireContext(), "You have not filled in the fields for entry!", Toast.LENGTH_SHORT).show()
            } else {
                lifecycleScope.launch {
                    val user = getUserFromDb(_binding?.loginEt?.text.toString(), dao) // Получаем пользователя из БД
                    if (user != null && user.password == _binding?.passwordEt?.text.toString()){
                        userPrefs.saveUser(user) // Сохраняем пользователя в SharedPreferences
                        viewModel.handleAction(SignInFragmentActions.GoToMainScreen)
                    }
                    else {
                        Toast.makeText(requireContext(), "Invalid login or password!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        _binding?.asiRegisterBtn?.setOnClickListener {
            viewModel.handleAction(SignInFragmentActions.GoToRegistrationScreen)
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