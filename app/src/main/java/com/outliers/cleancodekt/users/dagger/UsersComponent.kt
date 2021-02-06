package com.outliers.cleancodekt.users.dagger

import com.outliers.cleancodekt.framework.dagger.ActivityScope
import com.outliers.cleancodekt.users.ui.UsersActivity
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [UsersModule::class]) // tells Dagger that it is a subcomponent. But to tell that it depends on CCAppComponent
// a new Module (SubComponentsModule) has to be created that module is then passed to
// CCAppComp's modules list
interface UsersComponent {

    @Subcomponent.Factory // tells Dagger Component how to create this subcomponent
    interface Factory{
        fun create(): UsersComponent
    }

    fun inject(activity: UsersActivity)
}