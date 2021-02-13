package com.outliers.cleancodekt.userprofile.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.google.android.material.tabs.TabLayoutMediator
import com.outliers.cleancodekt.R
import com.outliers.cleancodekt.databinding.ActivityUserBinding
import com.outliers.cleancodekt.framework.CCApplication
import com.outliers.cleancodekt.framework.CCKtParentActivity
import com.outliers.cleancodekt.userprofile.albums.ui.AlbumsFragment
import com.outliers.cleancodekt.userprofile.dagger.UserProfileComponent
import com.outliers.cleancodekt.userprofile.interfaces.IUserProfile
import com.outliers.cleancodekt.userprofile.posts.ui.PostsFragment
import com.outliers.cleancodekt.userprofile.todos.ui.TodosFragment
import com.outliers.cleancodekt.userprofile.viewmodels.UserProfileVMFactory
import com.outliers.cleancodekt.userprofile.viewmodels.UserProfileViewModel
import com.outliers.cleancodekt.users.adapters.UserContentFragAdapter
import com.outliers.cleancodekt.users.models.UserModel
import javax.inject.Inject

class UserProfileActivity : CCKtParentActivity(),
        UserContentFragAdapter.UserContentFragAdapterParent, IUserProfile {

    lateinit var userTabNames: Array<String>
    val userTabNameKeys = arrayOf("posts", "albums", "todos")
    private val binding by lazy { ActivityUserBinding.inflate(layoutInflater) }

    @Inject
    lateinit var viewModelFactory: UserProfileVMFactory
    @Inject
    lateinit var fragAdapterFactory: UserProfileComponent.UserContentFragAdapterFactory
    lateinit var userProfileComponent: UserProfileComponent
    val viewModel: UserProfileViewModel by lazy {
        ViewModelProviders.of(
                this, viewModelFactory).get(UserProfileViewModel::class.java)
    }
    val userModel: UserModel? by lazy { intent.getParcelableExtra("user_model") as UserModel? }

    override fun onCreate(onSavedInstanceState: Bundle?) {
        userProfileComponent = (application as CCApplication).appComponent.userProfileComponent().create()
        userProfileComponent.inject(this)

        super.onCreate(onSavedInstanceState)
        setContentView(binding.root)

        userTabNames = arrayOf(getString(R.string.title_posts), getString(R.string.title_albums),
                getString(R.string.title_todos))
        setUpActionBar(userModel?.name)
        val fragAdapter = fragAdapterFactory.create(userTabNameKeys.size, this,
                supportFragmentManager, lifecycle)
        binding.userPages.adapter = fragAdapter
        binding.userPages.offscreenPageLimit = 2
        TabLayoutMediator(binding.userTabs, binding.userPages, true, true) { tab, position -> tab.setText(userTabNames[position]) }.attach()
        binding.userPages.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                viewModel.currFragLiveData.value = supportFragmentManager.findFragmentByTag("f$position")
            }
        })
    }

    override fun getFragByPos(position: Int): Fragment {
        return when (userTabNameKeys[position]) {
            "posts" -> PostsFragment()
            "albums" -> AlbumsFragment()
            else -> TodosFragment()
        }
    }

    fun setUpActionBar(title: String?) {
        setSupportActionBar(binding.toolbar)
        supportActionBar?.title = title
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    override fun getActivityComponent(): UserProfileComponent {
        return userProfileComponent
    }

    override fun getActivityViewModel(): UserProfileViewModel {
        return viewModel
    }
}