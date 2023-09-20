package com.example.myapplication

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.myapplication.databinding.ItemUserBinding

class UserDiffUtils() : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem === newItem
    }

    override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
        return oldItem == newItem
    }

}

class UserAdapter : PagingDataAdapter<User, UserAdapter.UserViewHolder>(UserDiffUtils()) {
    inner class UserViewHolder(val binding: ItemUserBinding) : ViewHolder(binding.root) {

        fun bind(user: User?) {
            binding.userNameTv.text = user?.getFullname()
            Glide.with(binding.root.context).load(user?.avatar).into(binding.userImage )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        return holder.bind(getItem(position))
    }
}