package com.example.loadermvp.mvp

/**
 * Created by Anton Shevchuk on 17.11.2017.
 */
public class Presenter(private val model: UserList.Model = Model(UserList.Model.State())) : UserList.Presenter, UserList.Model.Listener {
	private var view: UserList.View? = null

	override fun onViewAttached(view: UserList.View) {
		this.view = view
		this.model.setListener(this)
	}

	override fun onViewDetached() {
		this.view = null
	}

	override fun onCreated() {
		this.model.setListener(this)
		this.model.loadList()
	}

	override fun onDestroyed() {
		this.model.setListener(null)
	}

	//region Listener
	override fun onUpdate(state: UserList.Model.State) {
		this.view?.showUserList(state.userItems)
		if (state.isInProgress) {
			this.view?.showProgress()
		} else {
			this.view?.hideProgress()
		}
	}
	//endregion
}
