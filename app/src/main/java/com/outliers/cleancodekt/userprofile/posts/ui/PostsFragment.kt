package com.outliers.cleancodekt.userprofile.posts.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.outliers.cleancodekt.constants.Const
import com.outliers.cleancodekt.databinding.PostsListBinding
import com.outliers.cleancodekt.framework.RecyclerViewPaginator
import com.outliers.cleancodekt.userprofile.interfaces.IUserProfile
import com.outliers.cleancodekt.userprofile.posts.adapters.PostsRVAdapter

class PostsFragment : Fragment(), RecyclerViewPaginator.RecyclerPaginatorParent {

    val binding by lazy { PostsListBinding.inflate(layoutInflater) }
    val parent by lazy { activity as IUserProfile }
    val viewModel by lazy { parent.getActivityViewModel() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        parent.getActivityComponent().inject(this)
        super.onCreateView(inflater, container, savedInstanceState)

        binding.rv.layoutManager = LinearLayoutManager(activity)
        binding.rv.apply {
            addOnScrollListener(
                    RecyclerViewPaginator(this, this@PostsFragment,
                            Const.PAGE_SIZE, Const.INIT_PAGE_NUM))
        }
        binding.srl.setOnRefreshListener { onRefresh() }
        observerVM()
        onRefresh()
        return binding.root
    }

    fun onRefresh() {
        viewModel.refreshPosts()
    }

    fun observerVM() {
        viewModel.postsLiveData.observe(this, Observer {
            if (binding.rv.adapter == null) {
                val adapter = viewModel.postsLiveData.value?.let { it1 -> PostsRVAdapter(it1) }
                binding.rv.adapter = adapter
            }
            binding.rv.adapter?.notifyDataSetChanged()
        })
    }

    override val isLoading: Boolean
        get() = binding.srl.isRefreshing
    override val isLastPage: Boolean
        get() = viewModel.isLastPost.value?:false

    override fun loadMore(page: Int, batchSize: Int) {
        viewModel.fetchPosts(page, batchSize)
    }
}