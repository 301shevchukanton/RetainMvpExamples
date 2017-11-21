package com.example.loadermvp

import android.os.Bundle
import android.support.v4.app.LoaderManager
import android.support.v4.content.Loader
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.loadermvp.loader.PresenterLoader
import com.example.loadermvp.mvp.PresenterFactoryImpl
import com.example.loadermvp.mvp.UserList
import com.example.loadermvp.recycler.UserItem
import com.example.loadermvp.recycler.UserListRecyclerAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), UserList.View,
		LoaderManager.LoaderCallbacks<UserList.Presenter> {
	private val LOADER_ID = 101

	private var presenter: UserList.Presenter?= null

	private lateinit var adapter: UserListRecyclerAdapter

	override fun onLoadFinished(loader: Loader<UserList.Presenter>?,
	                            presenter: UserList.Presenter?) {
		this.presenter = presenter
	}
	override fun onLoaderReset(loader: Loader<UserList.Presenter>?) {
		this.presenter = null
	}
	override fun onCreateLoader(p0: Int, p1: Bundle?): Loader<UserList.Presenter>? =
			PresenterLoader<UserList.Presenter>(this, PresenterFactoryImpl())

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		this.adapter = UserListRecyclerAdapter(emptyList())

		getSupportLoaderManager().initLoader<UserList.Presenter>(LOADER_ID, null, this);
		initRecyclerView()
	}

	override fun onStart() {
		super.onStart()
		presenter?.onViewAttached(this)
	}

	override fun onStop() {
		presenter?.onViewDetached()
		super.onStop()
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

