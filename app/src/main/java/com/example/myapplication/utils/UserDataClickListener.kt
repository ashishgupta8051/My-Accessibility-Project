package com.example.myapplication.utils

import com.example.myapplication.model.Users

interface UserDataClickListener {

    fun onUserClick(users: Users, position : Int)
}