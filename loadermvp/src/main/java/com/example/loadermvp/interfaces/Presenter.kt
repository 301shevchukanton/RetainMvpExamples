package com.example.loadermvp.interfaces

interface Presenter<View> {
	fun onViewAttached(view: View)
	fun onViewDetached()
	fun onCreated()
	fun onDestroyed()
}