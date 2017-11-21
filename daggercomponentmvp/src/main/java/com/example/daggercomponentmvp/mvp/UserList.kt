package com.example.retaininstancestate.mvp

import com.example.retaininstancestate.recycler.UserItem
import java.io.Serializable

/**
 * Created by Anton Shevchuk on 17.11.2017.
 */
interface UserList {

    interface Model {

	    class State(var userItems: List<UserItem> = emptyList(),
	                var isInProgress: Boolean = false,
	                var shouldUpdate: Boolean = true) : Serializable

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

    interface Presenter {
        fun setView(view: View)
        fun onCreate()
        fun onDestroy(isChangingConfigurations: Boolean)
        fun onShouldUpdateList()
    }
}