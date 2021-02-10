package com.outliers.cleancodekt.users.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.outliers.cleancodekt.constants.Const
import com.outliers.cleancodekt.databinding.ActivityUsersBinding
import com.outliers.cleancodekt.framework.CCApplication
import com.outliers.cleancodekt.framework.RecyclerViewPaginator
import com.outliers.cleancodekt.users.adapters.UsersRVAdapter
import com.outliers.cleancodekt.users.dagger.UsersComponent
import com.outliers.cleancodekt.users.repos.UsersRepo
import com.outliers.cleancodekt.users.viewmodels.UsersViewModel
import com.outliers.cleancodekt.users.viewmodels.UsersViewModelFactory
import javax.inject.Inject

class UsersActivity : AppCompatActivity(), RecyclerViewPaginator.RecyclerPaginatorParent {
    val binder: ActivityUsersBinding by lazy { ActivityUsersBinding.inflate(layoutInflater) }
    lateinit var usersComponent: UsersComponent
    lateinit var usersViewModel: UsersViewModel
    @Inject
    lateinit var usersViewModelFactory: UsersViewModelFactory
    @Inject
    lateinit var usersRepo: UsersRepo
    var adapter: UsersRVAdapter? = null

    //@Inject lateinit var
    override fun onCreate(savedInstanceState: Bundle?) {
        usersComponent = (applicationContext as CCApplication).appComponent.usersComponent().create()
        usersComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binder.root)
        binder.rvUsers.layoutManager = LinearLayoutManager(this)
        binder.rvUsers.addOnScrollListener(RecyclerViewPaginator(binder.rvUsers, this,
                Const.PAGE_SIZE, Const.INIT_PAGE_NUM))
        usersViewModel = ViewModelProviders.of(this,
                usersViewModelFactory).get(UsersViewModel::class.java)
        observeVM()
        binder.srlUsers.setOnRefreshListener {  onRefresh() }
        onRefresh()
    }

    fun onRefresh(){
        binder.srlUsers.isRefreshing = true
        usersViewModel.refresh()
    }

    fun observeVM() {
        usersViewModel.usersLiveData.observe(this, Observer {
            if(adapter == null) {
                adapter = UsersRVAdapter(usersViewModel.listUsers)
                binder.rvUsers.adapter = adapter
            }
            adapter?.notifyDataSetChanged()
            binder.srlUsers.isRefreshing = false
        })
    }

    override val isLoading: Boolean
        get() = binder.srlUsers.isRefreshing
    override val isLastPage: Boolean
        get() = usersViewModel.isLastPage.value ?: false

    override fun loadMore(page: Int, batchSize: Int) {
        binder.srlUsers.isRefreshing = true
        usersViewModel.fetchUsers(page, batchSize)
    }
}