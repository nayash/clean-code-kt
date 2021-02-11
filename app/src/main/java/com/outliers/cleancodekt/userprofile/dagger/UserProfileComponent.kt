package com.outliers.cleancodekt.userprofile.dagger

import com.outliers.cleancodekt.framework.dagger.ActivityScope
import com.outliers.cleancodekt.userprofile.ui.UserProfileActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules=[UserProfileModule::class])
interface UserProfileComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): UserProfileComponent
    }

    fun inject(userProfileActivity: UserProfileActivity)
}