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
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.reddit.R
import com.example.reddit.data.storage.UserPreferences
import com.example.reddit.data.storage.room.DatabaseProvider
import com.example.reddit.databinding.FragmentHomeBinding
import com.example.reddit.domain.model.Post
import com.example.reddit.presentation.actions.HomeFragmentActions
import com.example.reddit.presentation.view.Adapter
import com.example.reddit.presentation.view.PostAdapter
import com.example.reddit.presentation.view.activities.MainActivity
import com.example.reddit.presentation.view_models.HomeFragmentViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.androidx.viewmodel.ext.android.viewModel
class HomeFragment : Fragment() {

    private val viewModel: HomeFragmentViewModel by viewModel()
    private var listOfPosts: ArrayList<Post> = ArrayList()
    private var postAdapter: PostAdapter? = null

    private var _binding: FragmentHomeBinding?= null
    private val binding: FragmentHomeBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // Делаем BottomNavigationView видимым
        (requireActivity() as MainActivity).binding.bottomNavigation.visibility = View.VISIBLE

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


        postAdapter = PostAdapter()
        binding.fmPostsRv.apply {
            adapter = postAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }



//        //usage of Adapter
//        val recyclerView = _binding?.fmPostsRv
//        adapter = Adapter(listOfPosts) { view, position: Int ->
//            //showPopupMenu(view, position, dao, currentUser)
//        }
//        recyclerView?.adapter = adapter
//        recyclerView?.layoutManager = LinearLayoutManager(requireContext())


        initClickListeners()

//        // setting of notesList
//        if (currentUser != null) {
//            lifecycleScope.launch(Dispatchers.IO) {
//                val updatedListOfPosts = dao.getPostsByUserId(currentUser.id)
//                withContext(Dispatchers.Main) { // Переключаемся на главный поток перед обновлением LiveData
//                    viewModel.handleAction(HomeFragmentActions.SetListOfPosts(updatedListOfPosts))
//                }
//            }
//        }

        observeViewModel(userPrefs)
    }

    private fun observeViewModel(userPrefs : UserPreferences) {
        viewModel.liveData.observe(viewLifecycleOwner) { state ->
            state?.let {
                if (it.checkSavedPostsBtn) {
                    findNavController().navigate(R.id.action_homeFragment_to_favoritesFragment)
//                    toNextScreen(FavoritesFragment(), "NewNoteFragment")
                }
                if (it.signOutBtn) {
                    userPrefs.deleteUser()
                    findNavController().navigate(R.id.action_homeFragment_to_signInFragment)
//                    toNextScreen(SignInFragment(), "SignInFragment")
                }
                if (it.readFullPostBtn){
                    //toNextScreen(ExactPostFragment(), "ExactPostFragment")
                }
                //updateListOfPosts(it.listOfPosts)
                // Обновляем адаптер, если данные изменились
                it.listOfPosts.observe(viewLifecycleOwner) { pagingData ->
                    postAdapter?.submitData(lifecycle, pagingData)
                }
            }
        }
    }

//    private fun toNextScreen(fragment : Fragment, fragmentTag : String){
////        parentFragmentManager.beginTransaction()
////            .replace(R.id.fragmentContainerView, fragment, fragmentTag)
////            .commit()
//    }

//    private fun updateListOfPosts(noteList: List<Post>?) {
//        if (noteList != null) {
//            listOfPosts.clear()
//            listOfPosts.addAll(noteList)
//            adapter?.notifyDataSetChanged()
//        }
//    }

    private fun initClickListeners() {
//        _binding?.fmMenuBtn?.setOnClickListener {
//            view?.let { it1 -> showPopupMenu(it1) }
//        }
        _binding?.fmSignOutBtn?.setOnClickListener {
            viewModel.handleAction(HomeFragmentActions.ReturnToRegistration)
        }
    }

//    private fun showPopupMenu(view: View) {   //, position: Int, dao: PostDao, currentUser : User?
//        val popupMenu = PopupMenu(view.context, view)
//        popupMenu.menuInflater.inflate(R.menu.three_points_btn_menu, popupMenu.menu)
//        popupMenu.setOnMenuItemClickListener { menuItem: MenuItem ->
//            when (menuItem.itemId) {
//                R.id.menu_saved -> {
//                    viewModel.handleAction(HomeFragmentActions.CheckSavedPosts)
//                    true
//                }
//
//                R.id.menu_sign_out -> {
//                    viewModel.handleAction(HomeFragmentActions.ReturnToRegistration)
//                    true
//                }
//
//                else -> false
//            }
//        }
//        popupMenu.show()
//    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }
}