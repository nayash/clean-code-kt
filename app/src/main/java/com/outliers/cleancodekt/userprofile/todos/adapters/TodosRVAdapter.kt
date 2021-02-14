package com.outliers.cleancodekt.userprofile.albums.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.outliers.cleancodekt.R
import com.outliers.cleancodekt.databinding.ItemTodoBinding
import com.outliers.cleancodekt.userprofile.todos.models.TodoModel

class TodosRVAdapter(val models: ArrayList<TodoModel>): RecyclerView.Adapter<TodosRVAdapter.TodoViewHolder>() {

    inner class TodoViewHolder(val binding: ItemTodoBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(todoModel: TodoModel){
            binding.apply {
                model = todoModel
            }.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(ItemTodoBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(models[position])
        if(models[position].completed){
            holder.binding.tvStatus.let {
                it.setText(it.context.getString(R.string.text_completed))
                it.setTextColor(it.context.resources.getColor(android.R.color.holo_green_light))
            }
        }else{
            holder.binding.tvStatus.let {
                it.setText(it.context.getString(R.string.text_pending))
                it.setTextColor(it.context.resources.getColor(android.R.color.holo_red_light))
            }
        }
    }

    override fun getItemCount(): Int {
        return models.size
    }
}