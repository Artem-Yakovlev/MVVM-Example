package com.example.mvvm_test.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm_test.data.entity.User
import com.example.mvvm_test.databinding.ActivityMainBinding
import com.example.mvvm_test.ui.recyclerview.UserDiffUtilCallback
import com.example.mvvm_test.ui.recyclerview.UserRecyclerViewAdapter
import com.example.mvvm_test.viewmodel.UserViewModel
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var userViewModel: UserViewModel

    private lateinit var adapter: UserRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        initViewModel()
        binding.addRandomUser.setOnClickListener {
            userViewModel.insert(
                User(
                    "Randomized", Random.nextInt(0, 126), Random.nextInt(0, 2)
                )
            )
        }
    }

    private fun initRecyclerView() {
        binding.usersRecyclerview.layoutManager = LinearLayoutManager(this@MainActivity)
        adapter = UserRecyclerViewAdapter(ArrayList())
        binding.usersRecyclerview.adapter = adapter
    }

    private fun initViewModel() {
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.allUsers.observe(this, Observer {
            adapter.refreshData(it ?: ArrayList())
        })
    }
}
