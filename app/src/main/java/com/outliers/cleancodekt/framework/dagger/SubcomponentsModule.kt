package com.outliers.cleancodekt.framework.dagger

import com.outliers.cleancodekt.userprofile.dagger.UserProfileComponent
import com.outliers.cleancodekt.users.dagger.UsersComponent
import dagger.Module

@Module(subcomponents = [UsersComponent::class, UserProfileComponent::class])
class SubcomponentsModule {
}