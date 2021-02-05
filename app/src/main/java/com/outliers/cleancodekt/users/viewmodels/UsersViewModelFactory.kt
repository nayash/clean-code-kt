package com.outliers.cleancodekt.users.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.outliers.cleancodekt.users.repos.UsersRepo

class UsersViewModelFactory(val usersRepo: UsersRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(usersRepo::class.java).newInstance(usersRepo)
    }
}