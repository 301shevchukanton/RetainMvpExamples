package com.example.daggercomponentmvp.mvp

import com.example.daggercomponentmvp.App
import javax.inject.Inject

/**
 * Created by Anton Shevchuk on 17.11.2017.
 */
public class Presenter() : UserList.Presenter, UserList.Model.Listener {
	private lateinit var view: UserList.View
	@Inject
	lateinit var model: UserList.Model

	//region Presenter
	override fun setView(view: UserList.View) {
		this.view = view
	}

	override fun onCreate() {
		App.INSTANCE.injection.userListComponent().inject(this)
		this.model.setListener(this)
	}

	override fun onDestroy(isChangingConfigurations: Boolean) {
		this.model.setListener(null)
		if (!isChangingConfigurations) {
			App.INSTANCE.injection.releaseUserListComponent()
		}
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

		if(state.shouldUpdate) {
			this.model.loadList()
		}
	}
	//endregion
}
