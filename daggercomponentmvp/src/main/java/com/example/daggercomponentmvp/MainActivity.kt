package com.example.daggercomponentmvp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.example.retaininstancestate.mvp.Presenter
import com.example.retaininstancestate.mvp.UserList
import com.example.retaininstancestate.recycler.UserItem
import com.example.retaininstancestate.recycler.UserListRecyclerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.io.Serializable

class MainActivity : AppCompatActivity(), UserList.View {

	private lateinit var presenter: UserList.Presenter
	private lateinit var adapter: UserListRecyclerAdapter
	private val DATA_SERIALIZABLE_KEY = "DATA"


	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		this.adapter = UserListRecyclerAdapter(emptyList())
		this.presenter = Presenter()
		initPresenter()
		initRecyclerView()
	}

	private fun initPresenter() {
		this.presenter.setView(this)
		this.presenter.onCreate()
	}

	override fun onDestroy() {
		this.presenter.onDestroy(isChangingConfigurations)
		super.onDestroy()
	}

	override fun onSaveInstanceState(outState: Bundle?) {
		super.onSaveInstanceState(outState)
		if (adapter.list.isNotEmpty()) {
			outState?.putSerializable(
					DATA_SERIALIZABLE_KEY, adapter.list as Serializable)
		}
	}

	private fun initRecyclerView() {
		this.rvItems.layoutManager = LinearLayoutManager(this)
		this.rvItems.adapter = this.adapter
	}

	override fun showProgress() {
		progressBar.visibility = View.VISIBLE
	}

	override fun hideProgress() {
		progressBar.visibility = View.GONE
	}

	override fun showUserList(userList: List<UserItem>) {
		this.adapter.setItems(userList)
		this.adapter.notifyDataSetChanged()
	}
}

