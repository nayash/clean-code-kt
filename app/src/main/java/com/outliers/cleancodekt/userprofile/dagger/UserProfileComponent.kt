package com.outliers.cleancodekt.userprofile.dagger

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import com.outliers.cleancodekt.framework.dagger.ActivityScope
import com.outliers.cleancodekt.userprofile.ui.UserProfileActivity
import com.outliers.cleancodekt.users.adapters.UserContentFragAdapter
import dagger.Subcomponent
import dagger.assisted.AssistedFactory

@ActivityScope
@Subcomponent(modules=[UserProfileModule::class])
interface UserProfileComponent {

    @AssistedFactory
    interface UserContentFragAdapterFactory{
        fun create(fragsCount: Int, parent: UserContentFragAdapter.UserContentFragAdapterParent,
        fragmentManager: FragmentManager, lifecycle: Lifecycle): UserContentFragAdapter
    }

    @Subcomponent.Factory
    interface Factory {
        fun create(): UserProfileComponent
    }

    fun inject(userProfileActivity: UserProfileActivity)
}