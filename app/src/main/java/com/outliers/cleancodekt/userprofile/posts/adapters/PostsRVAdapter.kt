package com.outliers.cleancodekt.userprofile.posts.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.outliers.cleancodekt.databinding.ItemPostBinding
import com.outliers.cleancodekt.userprofile.posts.models.PostModel

class PostsRVAdapter(val models: ArrayList<PostModel>): RecyclerView.Adapter<PostsRVAdapter.PostViewHolder>() {
    interface IAdapter{
        fun onItemClicked(position: Int, model: PostModel)
    }

    inner class PostViewHolder(private val binding: ItemPostBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(model: PostModel){
            binding.post = model
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        return PostViewHolder(ItemPostBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.bind(models[position])
    }

    override fun getItemCount(): Int {
        return models.size
    }
}