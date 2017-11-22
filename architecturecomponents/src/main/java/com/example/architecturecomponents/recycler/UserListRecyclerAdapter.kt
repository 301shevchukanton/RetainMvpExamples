package com.example.architecturecomponents.recycler

import android.R
import android.arch.lifecycle.MutableLiveData
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.recycler.TodoItemViewHolder

/**
 * Created by Anton Shevchuk on 17.11.2017.
 */

class UserListRecyclerAdapter(var list: List<UserItem> = emptyList()) : RecyclerView.Adapter<TodoItemViewHolder>() {

    val itemClick = MutableLiveData<UserItem>()

    public fun setItems(list: List<UserItem>) {
        this.list = list
    }

    override fun getItemCount(): Int {
        return list.count()
    }

    override fun onBindViewHolder(holder: TodoItemViewHolder, position: Int) {
        holder.titleTextView.text = this.list[position].userName
        holder.bind(this.itemClick,this.list[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoItemViewHolder {
        return TodoItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.simple_list_item_1, parent, false))
    }

}