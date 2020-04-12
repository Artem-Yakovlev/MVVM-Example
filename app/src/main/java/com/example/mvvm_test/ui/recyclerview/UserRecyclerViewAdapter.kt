package com.example.mvvm_test.ui.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm_test.data.entity.User
import com.example.mvvm_test.databinding.CardviewUserBinding


class UserRecyclerViewAdapter(
    var users: ArrayList<User>
) :
    RecyclerView.Adapter<UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UserViewHolder(
            CardviewUserBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )


    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bindDate(users[position])
    }

    override fun getItemCount() = users.size

    fun refreshData(users: List<User>) {
        val diffResult = DiffUtil.calculateDiff(UserDiffUtilCallback(this.users, users))

        this.users.clear()
        this.users.addAll(users)

        diffResult.dispatchUpdatesTo(this)
    }
}