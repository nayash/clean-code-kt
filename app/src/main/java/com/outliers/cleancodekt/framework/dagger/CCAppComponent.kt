package com.outliers.cleancodekt.framework.dagger

import com.outliers.cleancodekt.users.dagger.UsersComponent
import dagger.Component

@Component(modules = [NetworkModule::class, SubcomponentsModule::class])
interface CCAppComponent {
    fun usersComponent(): UsersComponent.Factory
}