package com.example.mvvm_test.ui.recyclerview

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_test.data.entity.User
import com.example.mvvm_test.databinding.CardviewUserBinding

class UserViewHolder(
    private val binding: CardviewUserBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindDate(user: User) {
        binding.userId.text = user.id.toString()
        binding.userAge.text = user.age.toString()
        binding.userName.text = user.name
    }


}