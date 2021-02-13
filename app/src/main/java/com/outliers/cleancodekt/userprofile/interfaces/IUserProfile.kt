package com.outliers.cleancodekt.userprofile.interfaces

import com.outliers.cleancodekt.userprofile.dagger.UserProfileComponent
import com.outliers.cleancodekt.userprofile.viewmodels.UserProfileViewModel

interface IUserProfile {
    fun getActivityComponent(): UserProfileComponent
    fun getActivityViewModel(): UserProfileViewModel
}