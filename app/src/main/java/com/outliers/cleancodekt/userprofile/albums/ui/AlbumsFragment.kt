package com.outliers.cleancodekt.userprofile.albums.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.outliers.cleancodekt.databinding.AlbumsListBinding

class AlbumsFragment: Fragment() {

    val binding by lazy{ AlbumsListBinding.inflate(layoutInflater)}

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return binding.root
    }
}