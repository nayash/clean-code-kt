package com.outliers.cleancodekt.userprofile.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.outliers.cleancodekt.userprofile.network.UserProfileRepo
import com.outliers.cleancodekt.users.models.UserModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class UserProfileVMFactory @AssistedInject constructor(
    @Assisted val userModel: UserModel,
    val userProfileRepo: UserProfileRepo
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor(userModel::class.java, userProfileRepo::class.java)
            .newInstance(userModel, userProfileRepo)
    }
}