package com.example.loadermvp.mvp

import com.example.loadermvp.interfaces.Presenter
import com.example.loadermvp.recycler.UserItem
import java.io.Serializable

/**
 * Created by Anton Shevchuk on 17.11.2017.
 */
interface UserList {

	interface Model {

		class State(var userItems: List<UserItem> = emptyList(),
		            var isInProgress: Boolean = false) : Serializable

		fun loadList()
		fun setListener(listener: Listener?)

		interface Listener {
			fun onUpdate(state: State)
		}
	}

	interface View {
		fun showProgress()
		fun hideProgress()
		fun showUserList(userList: List<UserItem>)
	}

	interface Presenter : com.example.loadermvp.interfaces.Presenter<View>
}