package com.example.loadermvp.loader

import android.content.Context
import android.support.v4.content.Loader

class PresenterLoader<Presenter :
com.example.loadermvp.interfaces.Presenter<*>>(context: Context,
                                               private val factory: PresenterFactory<Presenter>) : Loader<Presenter>(context) {
	private var presenter: Presenter? = null

	override fun onStartLoading() {

		if (presenter != null) {
			deliverResult(presenter)
			return
		}
		forceLoad()
	}

	override fun onForceLoad() {
		presenter = factory.create()
		presenter!!.onCreated()
		deliverResult(presenter)
	}

	override fun onReset() {
		presenter!!.onDestroyed()
		presenter = null
	}
}