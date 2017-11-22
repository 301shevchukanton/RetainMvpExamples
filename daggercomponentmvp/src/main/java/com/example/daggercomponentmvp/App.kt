package com.example.daggercomponentmvp

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.example.daggercomponentmvp.injection.Injection

class App : Application() {
	companion object {
		lateinit var INSTANCE: App
			private set
	}

	lateinit var injection: Injection
		private set

	override fun onCreate() {
		super.onCreate()
		INSTANCE = this
		injection = Injection()
	}

	override fun onTerminate() {
		super.onTerminate()
	}

	override fun attachBaseContext(base: Context?) {
		super.attachBaseContext(base)
		MultiDex.install(this)
	}
}