package com.example.daggercomponentmvp.injection.component

import com.example.daggercomponentmvp.injection.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
		ApplicationModule::class))

interface AppComponent {
	fun userListComponent(): UserListComponent
}