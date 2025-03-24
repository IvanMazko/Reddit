package com.example.reddit.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.reddit.data.api.objects.PostResponse
import com.example.reddit.databinding.RecyclerViewExactPostBinding
import androidx.recyclerview.widget.DiffUtil
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PostAdapter : PagingDataAdapter<PostResponse, PostAdapter.ViewHolder>(DiffCallBack()) {

    class ViewHolder(val binding : RecyclerViewExactPostBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        private val dateFormat = SimpleDateFormat("dd MMM yyyy, HH:mm", Locale.getDefault())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecyclerViewExactPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = getItem(position) // Вместо list[position], так как используется PagingDataAdapter
        post?.let {
            holder.binding.rvepAuthorActv.text = it.author
            holder.binding.rvepHeaderActv.text = it.title
            holder.binding.rvepMessageActv.text = it.selfText
            holder.binding.rvepDateActv.text = dateFormat.format(Date(it.createdUtc * 1000))  // умножаем на 1000, так как значение в секундах
        }
    }


    class DiffCallBack : DiffUtil.ItemCallback<PostResponse>() {
        override fun areItemsTheSame(oldItem: PostResponse, newItem: PostResponse): Boolean {
            return oldItem.permalink == newItem.permalink // Уникальный идентификатор поста
        }

        override fun areContentsTheSame(oldItem: PostResponse, newItem: PostResponse): Boolean {
            return oldItem == newItem // Проверка по содержимому
        }

    }



}