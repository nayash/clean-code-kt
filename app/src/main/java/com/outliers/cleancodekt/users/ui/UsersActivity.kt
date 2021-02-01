package com.outliers.cleancodekt.users.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.outliers.cleancodekt.databinding.ActivityUsersBinding

class UsersActivity : AppCompatActivity() {
    val binder: ActivityUsersBinding by lazy { ActivityUsersBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeVM()
    }

    fun observeVM(){

    }
}