package com.example.retaininstancestate.mvp

import com.example.retaininstancestate.mvp.Model
import com.example.retaininstancestate.mvp.UserList

/**
 * Created by Anton Shevchuk on 17.11.2017.
 */
public class Presenter(private val model: UserList.Model = Model(UserList.Model.State())) : UserList.Presenter, UserList.Model.Listener {
	private lateinit var view: UserList.View

	//region Presenter
	override fun setView(view: UserList.View) {
		this.view = view
	}

	override fun onCreate() {
		this.model.setListener(this)
	}

	override fun onDestroy() {
		this.model.setListener(null)
	}

	override fun onShouldUpdateList() {
		this.model.loadList()
	}
	//endregion

	//region Listener
	override fun onUpdate(state: UserList.Model.State) {
		this.view.showUserList(state.userItems)
		if (state.isInProgress) {
			this.view.showProgress()
		} else {
			this.view.hideProgress()
		}
	}
	//endregion
}
