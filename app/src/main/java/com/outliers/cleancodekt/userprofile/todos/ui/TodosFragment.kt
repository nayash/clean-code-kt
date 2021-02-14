package com.outliers.cleancodekt.userprofile.todos.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.outliers.cleancodekt.constants.Const
import com.outliers.cleancodekt.databinding.TodosListBinding
import com.outliers.cleancodekt.framework.RecyclerViewPaginator
import com.outliers.cleancodekt.userprofile.albums.adapters.TodosRVAdapter
import com.outliers.cleancodekt.userprofile.interfaces.IUserProfile

class TodosFragment: Fragment(), RecyclerViewPaginator.RecyclerPaginatorParent {

    val binding by lazy { TodosListBinding.inflate(layoutInflater) }
    val parent by lazy { activity as IUserProfile }
    val viewModel by lazy { parent.getActivityViewModel() }
    val userProfileComponent by lazy { parent.getActivityComponent() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        userProfileComponent.inject(this)
        super.onCreateView(inflater, container, savedInstanceState)

        binding.rv.apply {
            addOnScrollListener(
                RecyclerViewPaginator(
                    this, this@TodosFragment,
                    Const.PAGE_SIZE, Const.INIT_PAGE_NUM
                )
            )
            layoutManager = LinearLayoutManager(activity)
        }
        observeVM()
        onRefresh()

        return binding.root
    }

    fun observeVM() {
        viewModel.todosLiveData.observe(this, Observer {
            if(binding.rv.adapter == null){
                binding.rv.adapter = viewModel.todosLiveData.value?.let { it1 ->
                    TodosRVAdapter(it1)
                }
            }
            binding.srl.isRefreshing = false
            binding.rv.adapter?.notifyDataSetChanged()
        })
    }

    fun onRefresh() {
        binding.srl.isRefreshing = true
        viewModel.refreshTodos()
    }

    override val isLoading: Boolean
        get() = binding.srl.isRefreshing
    override val isLastPage: Boolean
        get() = viewModel.isLastTodo.value == true

    override fun loadMore(page: Int, batchSize: Int) {
        viewModel.fetchTodos(page, batchSize)
    }
}