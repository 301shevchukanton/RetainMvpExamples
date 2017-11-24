package com.example.architecturecomponents

/**
 * Created by Anton Shevchuk on 22.11.2017.
 */

class ViewModelError<Error>(private var error: Error) {
	private var isHandled: Boolean = false

	fun handle(action : (Error) -> Unit) {
		if (!isHandled) {
			isHandled = true
			action.invoke(error)
		}
	}
}
