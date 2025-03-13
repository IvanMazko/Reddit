package com.example.reddit.presentation.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reddit.R
import com.example.reddit.data.models.User
import com.example.reddit.data.storage.UserPreferences
import com.example.reddit.data.storage.room.DatabaseProvider
import com.example.reddit.data.storage.room.PostDao
import com.example.reddit.databinding.FragmentMainBinding
import com.example.reddit.domain.model.Post
import com.example.reddit.presentation.actions.MainFragmentActions
import com.example.reddit.presentation.view.Adapter
import com.example.reddit.presentation.view_models.MainFragmentViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainFragment : Fragment() {

    private val viewModel: MainFragmentViewModel by viewModels()
    private var listOfPosts: ArrayList<Post> = ArrayList()
    private var adapter: Adapter? = null

    private var _binding: FragmentMainBinding?= null
    private val binding: FragmentMainBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // connecting of DB and Prefs
        val userPrefs = UserPreferences(requireContext())
        val database = DatabaseProvider.getDatabase(requireContext())
        val dao = database.postDao()

//        // setting of username
          val currentUser = userPrefs.getUser()
//        if (currentUser!=null){
//            val username = currentUser.username
//            viewModel.handleAction(MainFragmentAction.SetUserName(username))
//        }


        //usage of Adapter
        val recyclerView = _binding?.fmPostsRv
        adapter = Adapter(listOfPosts) { view, position: Int ->
            //showPopupMenu(view, position, dao, currentUser)
        }
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(requireContext())


        initClickListeners()

        // setting of notesList
        if (currentUser != null) {
            lifecycleScope.launch(Dispatchers.IO) {
                val updatedListOfPosts = dao.getPostsByUserId(currentUser.id)
                withContext(Dispatchers.Main) { // Переключаемся на главный поток перед обновлением LiveData
                    viewModel.handleAction(MainFragmentActions.SetListOfPosts(updatedListOfPosts))
                }
            }
        }

        observeViewModel(userPrefs)
    }

    private fun observeViewModel(userPrefs : UserPreferences) {
        viewModel.liveData.observe(viewLifecycleOwner) { state ->
            state?.let {
                if (it.checkSavedPostsBtn) {
                    //toNextScreen(SavedPostsFragment(), "NewNoteFragment")
                }
                if (it.signOutBtn) {
                    userPrefs.deleteUser()
                    toNextScreen(SignInFragment(), "SignInFragment")
                }
                if (it.readFullPostBtn){
                    //toNextScreen(ExactPostFragment(), "ExactPostFragment")
                }
                updateListOfPosts(it.listOfPosts)
            }
        }
    }

    private fun toNextScreen(fragment : Fragment, fragmentTag : String){
        parentFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainerView, fragment, fragmentTag)
            .commit()
    }

    private fun updateListOfPosts(noteList: List<Post>?) {
        if (noteList != null) {
            listOfPosts.clear()
            listOfPosts.addAll(noteList)
            adapter?.notifyDataSetChanged()
        }
    }

    private fun initClickListeners() {
        _binding?.fmMenuBtn?.setOnClickListener {
            view?.let { it1 -> showPopupMenu(it1) }
        }
//        _binding?.amSignOutBtn?.setOnClickListener {
//            viewModel.handleAction(MainFragmentAction.ReturnToRegistration)
//        }
    }

    private fun showPopupMenu(view: View) {   //, position: Int, dao: PostDao, currentUser : User?
        val popupMenu = PopupMenu(view.context, view)
        popupMenu.menuInflater.inflate(R.menu.three_points_btn_menu, popupMenu.menu)
        popupMenu.setOnMenuItemClickListener { menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.menu_saved -> {
                    viewModel.handleAction(MainFragmentActions.CheckSavedPosts)
                    true
                }

                R.id.menu_sign_out -> {
                    viewModel.handleAction(MainFragmentActions.ReturnToRegistration)
                    true
                }

                else -> false
            }
        }
        popupMenu.show()
    }
}