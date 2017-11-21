package com.example.daggercomponentmvp.injection

import android.content.Context
import com.example.daggercomponentmvp.injection.component.AppComponent
import com.example.daggercomponentmvp.injection.component.DaggerAppComponent
import com.example.daggercomponentmvp.injection.component.UserListComponent
import com.example.daggercomponentmvp.injection.module.ApplicationModule

class Injection(context: Context) {

	private val applicationComponent: AppComponent = DaggerAppComponent.builder()
			.applicationModule(ApplicationModule(context))
			.build()

	fun applicationComponent(): AppComponent = applicationComponent

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