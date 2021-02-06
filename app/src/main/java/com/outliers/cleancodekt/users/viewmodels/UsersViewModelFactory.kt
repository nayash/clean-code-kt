package com.outliers.cleancodekt.users.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.outliers.cleancodekt.users.repos.UsersRepo
import javax.inject.Inject

class UsersViewModelFactory @Inject constructor(val usersRepo: UsersRepo): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(usersRepo::class.java).newInstance(usersRepo)
    }
}