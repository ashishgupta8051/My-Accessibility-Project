package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.UsersItemListBinding
import com.example.myapplication.model.Users
import com.example.myapplication.utils.UserDataClickListener

class UsersAdapter(private val list: List<Users>, private val userDataClickListener: UserDataClickListener) : RecyclerView.Adapter<UsersAdapter.UsersViewHolder>() {

    inner class UsersViewHolder(binding: UsersItemListBinding) : RecyclerView.ViewHolder(binding.root) {
        private val binding: UsersItemListBinding? = DataBindingUtil.bind(itemView)

        fun onBind(users: Users){
            binding!!.users = users
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersViewHolder {
        val binding: UsersItemListBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.users_item_list, parent, false)
        return UsersViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: UsersViewHolder, position: Int) {
        val users = list[position]
        holder.onBind(users)

        holder.itemView.setOnClickListener {
            userDataClickListener.onUserClick(users, position)
        }
    }

}