package com.example.myapplication.ui.activity

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.view.View.OnClickListener

import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.ui.fragment.UsersDetailsFragment
import com.example.myapplication.ui.fragment.UsersFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnClickListener{
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.hide()

        binding.tvPermission.setOnClickListener(this)

        val fragment = UsersFragment()
        val fragmentManager = supportFragmentManager
        fragmentManager.beginTransaction().add(R.id.container, fragment).commit()
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.tv_permission -> {
                takePermission()
            }
        }
    }

    private fun takePermission() {
        startActivity(Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS))
    }
}