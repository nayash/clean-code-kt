package com.outliers.cleancodekt.userprofile.dagger

import dagger.Subcomponent

@Subcomponent(modules=[UserProfileModule::class])
interface UserProfileComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): UserProfileComponent
    }
}