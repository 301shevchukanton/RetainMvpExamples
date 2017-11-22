package com.example.daggercomponentmvp.injection.module

import com.example.daggercomponentmvp.mvp.Model
import com.example.daggercomponentmvp.mvp.UserList
import dagger.Module
import dagger.Provides

@Module
class UserListModule {
	@com.example.daggercomponentmvp.injection.scope.UserList
	@Provides
	fun userListModel(): UserList.Model = Model(UserList.Model.State())
}