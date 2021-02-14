package com.outliers.cleancodekt.userprofile.albums.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.outliers.cleancodekt.databinding.ItemAlbumBinding
import com.outliers.cleancodekt.userprofile.albums.models.AlbumModel

class AlbumsRVAdapter(val models: ArrayList<AlbumModel>): RecyclerView.Adapter<AlbumsRVAdapter.AlbumViewHolder>() {

    inner class AlbumViewHolder(val binding: ItemAlbumBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(albumModel: AlbumModel){
            binding.apply {
                model = albumModel
            }.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumViewHolder {
        return AlbumViewHolder(ItemAlbumBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        holder.bind(models[position])
    }

    override fun getItemCount(): Int {
        return models.size
    }
}