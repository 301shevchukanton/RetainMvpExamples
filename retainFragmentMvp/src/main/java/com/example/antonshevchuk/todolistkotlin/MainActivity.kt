package com.example.antonshevchuk.todolistkotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.antonshevchuk.todolistkotlin.mvp.Presenter
import com.example.antonshevchuk.todolistkotlin.mvp.UserList
import com.example.antonshevchuk.todolistkotlin.recycler.UserItem
import com.example.antonshevchuk.todolistkotlin.recycler.UserListRecyclerAdapter
import com.example.antonshevchuk.todolistkotlin.retain.DataFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), UserList.View {

	private lateinit var adapter: UserListRecyclerAdapter
	private var presenter: UserList.Presenter? = null
	private var dataFragment: DataFragment<UserList.Presenter>? = null
	private val RETAIN_FRAGMENT_TAG = "RETAIN_FRAGMENT_TAG"

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		val fragmentManager = getFragmentManager()
		this.dataFragment = fragmentManager.findFragmentByTag(RETAIN_FRAGMENT_TAG)
				as? DataFragment<UserList.Presenter>
		this.adapter = UserListRecyclerAdapter(emptyList())

		val dataCreatedForTheFirstTime = this.dataFragment == null || this.dataFragment?.data == null
		if (dataCreatedForTheFirstTime) { //if no previously data saved - create data fragment
			this.dataFragment = DataFragment<UserList.Presenter>()
			fragmentManager.beginTransaction().add(this.dataFragment, RETAIN_FRAGMENT_TAG).commit()
			this.presenter = Presenter()
			initPresenter()
			this.presenter?.onShouldUpdateList()
		} else {  // restore presenter from fragment's data
			this.presenter = this.dataFragment?.data
			initPresenter()
		}
		initRecyclerView()
	}

	private fun initPresenter() {
		this.presenter?.setView(this)
		this.presenter?.onCreate()
	}

	override fun onDestroy() {
		this.dataFragment?.data = this.presenter
		this.presenter?.onDestroy()
		super.onDestroy()
	}

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

