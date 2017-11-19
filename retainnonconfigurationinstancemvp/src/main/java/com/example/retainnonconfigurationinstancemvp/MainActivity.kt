package com.example.retainnonconfigurationinstancemvp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.antonshevchuk.todolistkotlin.mvp.Presenter
import com.example.antonshevchuk.todolistkotlin.mvp.UserList
import com.example.antonshevchuk.todolistkotlin.recycler.UserItem
import com.example.antonshevchuk.todolistkotlin.recycler.UserListRecyclerAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), UserList.View {

	private lateinit var presenter: UserList.Presenter
	private lateinit var adapter: UserListRecyclerAdapter

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		this.adapter = UserListRecyclerAdapter(emptyList())

		val dataCreatedForTheFirstTime = lastCustomNonConfigurationInstance == null

		if (dataCreatedForTheFirstTime) {
			this.presenter = Presenter()
			initPresenter()
			this.presenter.onShouldUpdateList()
		} else {
			this.presenter = lastCustomNonConfigurationInstance
					as UserList.Presenter
			initPresenter()
		}
		initRecyclerView()
	}

	private fun initPresenter() {
		this.presenter.setView(this)
		this.presenter.onCreate()
	}

	override fun onDestroy() {
		this.presenter.onDestroy()
		super.onDestroy()
	}

	override fun onRetainCustomNonConfigurationInstance(): Any = presenter

	private fun initRecyclerView() {
		this.rvItems.layoutManager = LinearLayoutManager(this) as RecyclerView.LayoutManager?
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

