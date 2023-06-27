package com.example.myapplication.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.myapplication.R
import com.example.myapplication.adapter.UsersAdapter
import com.example.myapplication.databinding.FragmentUsersBinding
import com.example.myapplication.model.Users
import com.example.myapplication.utils.UserDataClickListener
import com.example.myapplication.utils.checkInternetConnection
import com.example.myapplication.viewmodel.UsersVM
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UsersFragment : Fragment() , UserDataClickListener {
    private lateinit var binding: FragmentUsersBinding
    private val usersVM : UsersVM by viewModels()
    private lateinit var usersAdapter: UsersAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentUsersBinding.inflate(inflater, container, false)

        binding.progressBar.visibility = View.VISIBLE
        usersVM.mLoder.value = true
        if (checkInternetConnection(requireContext())){
            usersVM.getOnlineUsers.observe(requireActivity()) { userList->
                binding.progressBar.visibility = View.GONE
                for (user in userList){
                    usersVM.addUsers(user)
                }

//                Log.e("DATA_1", userList.toString())
                usersAdapter = UsersAdapter(userList, this)
                binding.rvView.adapter = usersAdapter
            }
        }else{
            usersVM.getOfflineUsers.observe(requireActivity()) { userList->
                binding.progressBar.visibility = View.GONE
//                Log.e("DATA_2", userList.toString())
                usersAdapter = UsersAdapter(userList, this)
                binding.rvView.adapter = usersAdapter
            }
        }

        return binding.root
    }

    override fun onUserClick(users: Users, position: Int) {
        val fragment = UsersDetailsFragment()
        val bundle = Bundle()
        bundle.putParcelable("USERS",users)
        fragment.arguments = bundle
        val fragmentManager = requireActivity().supportFragmentManager
        fragmentManager.beginTransaction().replace(R.id.container, fragment).addToBackStack(null).commit()
    }

}