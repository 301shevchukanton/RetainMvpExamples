package com.example.daggercomponentmvp

import android.app.Application
import android.content.Context
import android.support.multidex.MultiDex
import com.example.daggercomponentmvp.injection.Injection


/**
 * Created by yaz on 2/7/17.
 */
//TODO: investigate if calculators should receive null objects
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

		initInjection()
	}

	override fun attachBaseContext(base: Context?) {
		super.attachBaseContext(base)
		MultiDex.install(this)
	}

	private fun initInjection() {
		injection = Injection(this)
	}
}