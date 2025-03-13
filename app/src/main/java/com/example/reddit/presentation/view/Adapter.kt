package com.example.reddit.presentation.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.reddit.databinding.RecyclerViewExactPostBinding
import com.example.reddit.domain.model.Post

class Adapter(
    private val list: List<Post>,
    private val callback: (view : View, position : Int) -> Unit
) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    class ViewHolder(val binding : RecyclerViewExactPostBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RecyclerViewExactPostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        with(holder.binding){
            rvepAuthorActv.text = list[position].author
            rvepMessageActv.text = list[position].message
            rvepDateActv.text = list[position].date
            root.setOnClickListener {
                callback.invoke(root, position)
            }
        }
    }

    override fun getItemCount(): Int = list.size
}

