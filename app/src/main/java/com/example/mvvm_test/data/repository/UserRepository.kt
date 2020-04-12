package com.example.mvvm_test.data.repository

import androidx.lifecycle.LiveData
import com.example.mvvm_test.data.dao.UserDao
import com.example.mvvm_test.data.entity.User

class UserRepository(private val userDao: UserDao) {

    val allUsers: LiveData<List<User>> = userDao.getAllUsers()

    suspend fun insert(user: User) {
        userDao.insert(user)
    }

    suspend fun deleteAll() {
        userDao.deleteAll()
    }

    companion object {

        @Volatile
        private var instance: UserRepository? = null

        fun getInstance(userDao: UserDao) =
            instance ?: synchronized(this) {
                instance ?: UserRepository(userDao).also { instance = it }
            }
    }
}