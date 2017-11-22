package com.example.architecturecomponents

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.architecturecomponents.recycler.UserItem
import com.example.architecturecomponents.recycler.UserListRecyclerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), LifecycleOwner {

	private lateinit var adapter: UserListRecyclerAdapter
	private val viewModelClass = UserListViewModel::class.java
	private var viewModel: UserListViewModel? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		this.adapter = UserListRecyclerAdapter(emptyList())
		this.viewModel = ViewModelProviders.of(this).get(viewModelClass)
		subscribeOnViewModelChanges()
		initRecyclerView()
		if (this.viewModel?.userListLiveData?.value?.userItems?.isEmpty() ?:true) {
			this.viewModel?.loadList()
		}
	}

	private fun subscribeOnViewModelChanges() {
		this.viewModel?.userListLiveData?.observe(this, Observer {
			showUserList(it?.userItems ?: emptyList())
			if (it?.isInProgress == true) {
				showProgress()
			} else {
				hideProgress()
			}
		})
	}

	override fun onDestroy() {
		this.viewModel?.userListLiveData?.removeObservers(this)
		super.onDestroy()
	}

	private fun initRecyclerView() {
		this.rvItems.layoutManager = LinearLayoutManager(this)
		this.rvItems.adapter = this.adapter
	}

	fun showProgress() {
		progressBar.visibility = View.VISIBLE
	}

	fun hideProgress() {
		progressBar.visibility = View.GONE
	}

	fun showUserList(userList: List<UserItem>) {
		this.adapter.setItems(userList)
		this.adapter.notifyDataSetChanged()
	}
}

