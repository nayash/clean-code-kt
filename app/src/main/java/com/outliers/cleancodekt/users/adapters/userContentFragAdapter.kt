package com.outliers.cleancodekt.users.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class userContentFragAdapter constructor(val totalFrags: Int,
                                         val parent: userContentFragAdapterParent,
                                         fragmentManager: FragmentManager,
                                         lifecycle: Lifecycle): FragmentStateAdapter(fragmentManager, lifecycle) {

    interface userContentFragAdapterParent{
        fun getFragByPos(position: Int): Fragment
    }

    override fun getItemCount(): Int {
        return totalFrags
    }

    override fun createFragment(position: Int): Fragment {
        return parent.getFragByPos(position)
    }
}