package com.outliers.cleancodekt.userprofile.ui

import android.os.Bundle
import android.os.PersistableBundle
import androidx.lifecycle.ViewModelProviders
import com.outliers.cleancodekt.databinding.ActivityUserBinding
import com.outliers.cleancodekt.framework.CCApplication
import com.outliers.cleancodekt.framework.CCKtParentActivity
import com.outliers.cleancodekt.userprofile.dagger.UserProfileComponent
import com.outliers.cleancodekt.userprofile.viewmodels.UserProfileVMFactory
import com.outliers.cleancodekt.userprofile.viewmodels.UserProfileViewModel
import javax.inject.Inject

class UserProfileActivity : CCKtParentActivity() {

    private val binding by lazy { ActivityUserBinding.inflate(layoutInflater) }
    @Inject
    lateinit var viewModelFactory: UserProfileVMFactory
    val viewModel: UserProfileViewModel by lazy { ViewModelProviders.of(
            this, viewModelFactory).get(UserProfileViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        val userProfileComponent: UserProfileComponent =
                (application as CCApplication).appComponent.userProfileComponent().create()
        super.onCreate(savedInstanceState, persistentState)
        setContentView(binding.root)

    }
}