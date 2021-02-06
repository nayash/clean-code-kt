package com.outliers.cleancodekt.users.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.outliers.cleancodekt.constants.Const
import com.outliers.cleancodekt.databinding.ActivityUsersBinding
import com.outliers.cleancodekt.framework.CCApplication
import com.outliers.cleancodekt.users.adapters.UsersRVAdapter
import com.outliers.cleancodekt.users.dagger.UsersComponent
import com.outliers.cleancodekt.users.repos.UsersRepo
import com.outliers.cleancodekt.users.viewmodels.UsersViewModel
import com.outliers.cleancodekt.users.viewmodels.UsersViewModelFactory
import javax.inject.Inject

class UsersActivity : AppCompatActivity() {
    val binder: ActivityUsersBinding by lazy { ActivityUsersBinding.inflate(layoutInflater) }
    lateinit var usersComponent: UsersComponent
    lateinit var usersViewModel: UsersViewModel
    @Inject
    lateinit var usersViewModelFactory: UsersViewModelFactory
    @Inject
    lateinit var usersRepo: UsersRepo
    lateinit var adapter: UsersRVAdapter

    //@Inject lateinit var
    override fun onCreate(savedInstanceState: Bundle?) {
        usersComponent = (applicationContext as CCApplication).appComponent.usersComponent().create()
        usersComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binder.root)
        usersViewModel = ViewModelProviders.of(this,
                usersViewModelFactory).get(UsersViewModel::class.java)
        observeVM()
        usersViewModel.fetchUsers(Const.INIT_PAGE_NUM, Const.PAGE_SIZE)
    }

    fun observeVM() {
        usersViewModel.usersLiveData.observe(this, Observer {
            if(adapter == null) // accessing lateinit var here causes UninitPropAccessException
                adapter = UsersRVAdapter(usersViewModel.listUsers)
            adapter.notifyDataSetChanged()
        })
    }
}