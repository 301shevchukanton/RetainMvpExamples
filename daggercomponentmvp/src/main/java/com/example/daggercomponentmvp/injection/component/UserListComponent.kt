package com.example.daggercomponentmvp.injection.component

import com.example.daggercomponentmvp.injection.module.UserListModule
import com.example.retaininstancestate.mvp.Presenter
import com.example.retaininstancestate.mvp.UserList
import dagger.Subcomponent

@com.example.daggercomponentmvp.injection.scope.UserList
@Subcomponent(modules = arrayOf(UserListModule::class))
interface UserListComponent {

	fun inject(activity: Presenter)

	fun userListModel(): UserList.Model
}
