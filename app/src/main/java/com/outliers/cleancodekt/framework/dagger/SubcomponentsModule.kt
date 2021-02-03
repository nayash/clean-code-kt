package com.outliers.cleancodekt.framework.dagger

import com.outliers.cleancodekt.users.dagger.UsersComponent
import dagger.Module

@Module(subcomponents = [UsersComponent::class])
class SubcomponentsModule {
}