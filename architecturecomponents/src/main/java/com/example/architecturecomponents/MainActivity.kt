package com.example.architecturecomponents

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.example.architecturecomponents.recycler.UserItem
import com.example.architecturecomponents.recycler.UserListRecyclerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

	private lateinit var adapter: UserListRecyclerAdapter
	private val viewModelClass = UserListViewModel::class.java

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		this.adapter = UserListRecyclerAdapter(emptyList())
		this.adapter
				.itemClick
				.observe(this, Observer {
					Toast.makeText(this, it?.userName, Toast.LENGTH_LONG).show()
				})
		subscribeOnViewModelChanges()
		initRecyclerView()
		if (userListViewModel().userListLiveData.value?.userItems?.isEmpty() != false) {
			userListViewModel().loadList()
		}
	}

	private fun userListViewModel() = ViewModelProviders.of(this).get(viewModelClass)

	private fun subscribeOnViewModelChanges() {

		userListViewModel()
				.userListLiveData
				.observe(this,
						Observer {
							showUserList(it?.userItems ?: emptyList())
							if (it?.isInProgress == true) {
								showProgress()
							} else {
								hideProgress()
							}
						})

		userListViewModel().errorLiveData.observe(this, Observer {
			it?.handle {
				Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
				userListViewModel().loadList()
			}
		})
	}

	override fun onDestroy() {
		userListViewModel().userListLiveData.removeObservers(this)
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

