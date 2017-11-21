package com.example.loadermvp.loader

import android.content.Context
import android.support.v4.content.Loader
import com.example.loadermvp.interfaces.Presenter

class PresenterLoader<PRESENTER : Presenter<*>>(context: Context,
                                                private val factory: PresenterFactory<PRESENTER>) :
		Loader<PRESENTER>(context) {

	private var presenter: PRESENTER? = null

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