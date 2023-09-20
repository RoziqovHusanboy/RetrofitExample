package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myapplication.databinding.ItemTodoBinding

class TodoDiff:DiffUtil.ItemCallback<Todo>(){
    override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
         return oldItem===newItem
    }

    override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
      return oldItem==newItem
    }

}
class TodoAdapter:ListAdapter<Todo,TodoAdapter.viewHolder>(TodoDiff()) {

    inner class viewHolder(val binding:ItemTodoBinding):ViewHolder(binding.root){
        fun bind(todo: Todo){
        binding.textView.text = todo.title
            binding.checkBox.isChecked = todo.completed
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val binding = ItemTodoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return viewHolder(binding)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}