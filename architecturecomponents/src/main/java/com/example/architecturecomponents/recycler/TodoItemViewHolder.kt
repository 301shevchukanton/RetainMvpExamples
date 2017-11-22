package com.example.recycler

import android.R.id.text1
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.architecturecomponents.recycler.RecyclerViewItemClickListener
import com.example.architecturecomponents.recycler.UserItem

/**
 * Created by AntonShevchuk on 21.08.2016.
 */
class TodoItemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val titleTextView: TextView by lazy { view.findViewById<TextView>(text1) }
    private val rootView: View = view
    private lateinit var userItem: UserItem

    fun bind(recyclerViewItemClickListener: RecyclerViewItemClickListener, userItem: UserItem) {
        this.userItem = userItem
        this.rootView.setOnClickListener {
            recyclerViewItemClickListener.onItemClick(userItem)
        }
    }
}