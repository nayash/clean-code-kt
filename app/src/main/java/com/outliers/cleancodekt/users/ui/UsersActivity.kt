package com.outliers.cleancodekt.users.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.outliers.cleancodekt.constants.Const
import com.outliers.cleancodekt.databinding.ActivityUsersBinding
import com.outliers.cleancodekt.framework.ApiCallState
import com.outliers.cleancodekt.framework.CCApplication
import com.outliers.cleancodekt.framework.CCKtParentActivity
import com.outliers.cleancodekt.framework.RecyclerViewPaginator
import com.outliers.cleancodekt.userprofile.ui.UserProfileActivity
import com.outliers.cleancodekt.users.adapters.UsersRVAdapter
import com.outliers.cleancodekt.users.dagger.UsersComponent
import com.outliers.cleancodekt.users.models.UserModel
import com.outliers.cleancodekt.users.repos.UsersRepo
import com.outliers.cleancodekt.users.viewmodels.UsersViewModel
import com.outliers.cleancodekt.users.viewmodels.UsersViewModelFactory
import javax.inject.Inject

class UsersActivity : CCKtParentActivity(), RecyclerViewPaginator.RecyclerPaginatorParent,
    UsersRVAdapter.UsersAdapterInterface {
    val binder: ActivityUsersBinding by lazy { ActivityUsersBinding.inflate(layoutInflater) }
    lateinit var usersComponent: UsersComponent
    lateinit var usersViewModel: UsersViewModel

    @Inject
    lateinit var usersViewModelFactory: UsersViewModelFactory

    @Inject
    lateinit var usersRepo: UsersRepo

    @Inject
    lateinit var usersRepo1: UsersRepo
    var adapter: UsersRVAdapter? = null

    //@Inject lateinit var
    override fun onCreate(savedInstanceState: Bundle?) {
        usersComponent =
            (applicationContext as CCApplication).appComponent.usersComponent().create()
        usersComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(binder.root)
        binder.rvUsers.layoutManager = LinearLayoutManager(this)
        binder.rvUsers.addOnScrollListener(
            RecyclerViewPaginator(
                binder.rvUsers, this,
                Const.PAGE_SIZE, Const.INIT_PAGE_NUM
            )
        )
        usersViewModel = ViewModelProviders.of(
            this,
            usersViewModelFactory
        ).get(UsersViewModel::class.java)
        observeVM()
        binder.srlUsers.setOnRefreshListener { onRefresh() }
        onRefresh()
    }

    fun onRefresh() {
        println("onRefresh called")
        usersViewModel.refresh()
    }

    fun observeVM() {
        usersViewModel.usersLiveData.observe(this, Observer {
            if (adapter == null) {
                adapter = UsersRVAdapter(usersViewModel.listUsers, this)
                binder.rvUsers.adapter = adapter
            }
            adapter?.notifyDataSetChanged()
            Log.v("test-observe", usersViewModel.listUsers.size.toString() + "")
        })
        usersViewModel.apiCallStateLiveData.let {
            it.observe(this, Observer {
                binder.srlUsers.isRefreshing = (it.name == ApiCallState.LOADING.name)
            })
        }
    }

    override val isLoading: Boolean
        get() = usersViewModel.apiCallStateLiveData.value?.name == ApiCallState.LOADING.name
    override val isLastPage: Boolean
        get() = usersViewModel.isLastPage.value ?: false

    override fun loadMore(page: Int, batchSize: Int) {
        Log.v("test-loadMore", page.toString())
        usersViewModel.fetchUsers(page, batchSize)
    }

    override fun itemClicked(position: Int, model: UserModel) {
        val userActivityIntent = Intent(this, UserProfileActivity::class.java)
        userActivityIntent.putExtra("user_model", model)
        startActivity(userActivityIntent)
    }
}