package com.example.myapplication.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentUsersBinding
import com.example.myapplication.databinding.FragmentUsersDetailsBinding
import com.example.myapplication.model.Users


class UsersDetailsFragment : Fragment() {
    private lateinit var binding: FragmentUsersDetailsBinding
    private lateinit var user: Users


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = this.arguments
        if (bundle != null){
            user = bundle.getParcelable("USERS")!!
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_users_details, container, false)

        binding.user = user

        return binding.root
    }

}