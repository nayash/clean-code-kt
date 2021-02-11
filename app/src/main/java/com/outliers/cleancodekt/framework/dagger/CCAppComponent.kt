package com.outliers.cleancodekt.framework.dagger

import com.outliers.cleancodekt.userprofile.dagger.UserProfileComponent
import com.outliers.cleancodekt.users.dagger.UsersComponent
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, SubcomponentsModule::class])
interface CCAppComponent {
    fun usersComponent(): UsersComponent.Factory
    fun userProfileComponent(): UserProfileComponent.Factory
}