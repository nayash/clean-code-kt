package com.outliers.cleancodekt.users.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class UserContentFragAdapter @AssistedInject constructor(@Assisted val totalFrags: Int,
                                                         @Assisted val parent: UserContentFragAdapterParent,
                                                         @Assisted fragmentManager: FragmentManager,
                                                         @Assisted lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {

    interface UserContentFragAdapterParent{
        fun getFragByPos(position: Int): Fragment
    }

    override fun getItemCount(): Int {
        return totalFrags
    }

    override fun createFragment(position: Int): Fragment {
        return parent.getFragByPos(position)
    }
}