package com.example.daggercomponentmvp.injection

import com.example.daggercomponentmvp.injection.component.AppComponent
import com.example.daggercomponentmvp.injection.component.DaggerAppComponent
import com.example.daggercomponentmvp.injection.component.UserListComponent

class Injection {

	private val applicationComponent: AppComponent = DaggerAppComponent.builder()
			.build()

	private var userListComponent: UserListComponent? = null

	fun userListComponent(): UserListComponent {
		if (userListComponent == null) {
			userListComponent = applicationComponent.userListComponent()
		}
		return userListComponent!!
	}

	fun releaseUserListComponent() {
		userListComponent = null
	}
}