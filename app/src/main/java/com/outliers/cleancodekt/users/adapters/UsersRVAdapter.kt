package com.outliers.cleancodekt.users.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.outliers.cleancodekt.R
import com.outliers.cleancodekt.databinding.ItemUserBinding
import com.outliers.cleancodekt.users.models.UserModel

class UsersRVAdapter(val models: List<UserModel>):RecyclerView.Adapter<UsersRVAdapter.UserViewHolder>() {

    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        lateinit var itemUserBinding: ItemUserBinding
        constructor(itemUserBinding: ItemUserBinding) : this(itemUserBinding.root) {
            this.itemUserBinding = itemUserBinding
        }

        fun bind(userModel: UserModel){
            itemUserBinding.user = userModel
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersRVAdapter.UserViewHolder {
        return UsersRVAdapter.UserViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user, null))
    }

    override fun onBindViewHolder(holder: UsersRVAdapter.UserViewHolder, position: Int) {
        holder.bind(models[position])
    }

    override fun getItemCount(): Int {
        return models.size
    }

}