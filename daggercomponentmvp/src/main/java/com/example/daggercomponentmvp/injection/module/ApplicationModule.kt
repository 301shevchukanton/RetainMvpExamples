package com.example.daggercomponentmvp.injection.module

import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule(private val context: Context) {

	@Provides
	fun context() = context

}