package com.outliers.cleancodekt.userprofile.dagger

import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import com.outliers.cleancodekt.framework.dagger.ActivityScope
import com.outliers.cleancodekt.userprofile.albums.ui.AlbumsFragment
import com.outliers.cleancodekt.userprofile.posts.ui.PostsFragment
import com.outliers.cleancodekt.userprofile.todos.ui.TodosFragment
import com.outliers.cleancodekt.userprofile.ui.UserProfileActivity
import com.outliers.cleancodekt.userprofile.viewmodels.UserProfileVMFactory
import com.outliers.cleancodekt.users.adapters.UserContentFragAdapter
import com.outliers.cleancodekt.users.models.UserModel
import dagger.Subcomponent
import dagger.assisted.AssistedFactory

@ActivityScope
@Subcomponent(modules=[UserProfileModule::class])
interface UserProfileComponent {

    @AssistedFactory
    interface UserContentFragAdapterFactory{
        fun create(fragsCount: Int, parent: UserContentFragAdapter.UserContentFragAdapterParent,
        fragmentManager: FragmentManager, lifecycle: Lifecycle): UserContentFragAdapter
    }

    @ActivityScope
    @AssistedFactory
    interface UserProfileVMFactoryFactory{ // VM needs Factory because it has dependencies outside graph,
        // the Factory itself needs a Factory because it depends on UserModel which activity provides from intent
        fun create(userModel: UserModel?): UserProfileVMFactory
    }

    @Subcomponent.Factory
    interface Factory {
        fun create(): UserProfileComponent
    }

    fun inject(userProfileActivity: UserProfileActivity)
    fun inject(postsFragment: PostsFragment)
    fun inject(albumsFragment: AlbumsFragment)
    fun inject(todosFragment: TodosFragment)
}