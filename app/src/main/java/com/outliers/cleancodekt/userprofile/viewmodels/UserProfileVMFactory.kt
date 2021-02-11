package com.outliers.cleancodekt.userprofile.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.outliers.cleancodekt.framework.dagger.ActivityScope
import javax.inject.Inject

@ActivityScope
class UserProfileVMFactory @Inject constructor(): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.getConstructor().newInstance()
    }
}