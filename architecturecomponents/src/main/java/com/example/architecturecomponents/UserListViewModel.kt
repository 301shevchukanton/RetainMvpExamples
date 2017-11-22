package com.example.architecturecomponents

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.os.AsyncTask
import com.example.architecturecomponents.recycler.UserItem
import java.io.Serializable
import java.util.*

/**
 * Created by AntonShevchuk on 22.11.2017.
 */

class UserListViewModel : ViewModel() {

	class State(var userItems: List<UserItem> = emptyList(),
	            var isInProgress: Boolean = false) : Serializable

	val userListLiveData = MutableLiveData<State>()
	val errorLiveData = MutableLiveData<ViewModelError<Throwable>>()

	init {
		this.userListLiveData.value = State(emptyList(), true)
	}

	fun loadList() {
		userListLiveData.value = State(emptyList(), true)
		val i = Random().nextInt() % 4
		if (i == 1) {
			loadData() //load data and push it to userListLiveData
		} else {
			loadError() //simulate load data error and push error to errorLiveData
		}
	}

	private fun loadData() {
		object : AsyncTask<Void, Void, List<String>>() {
			override fun doInBackground(vararg params: Void?): List<String> {
				Thread.sleep(5000)
				return listOf("Peter", "Mike", "John Doe", "Clark Kent", "Bruce Wayne", "Peter Parker")
			}

			override fun onPostExecute(result: List<String>?) {
				val list = result?.map { UserItem(it) }?.toList() ?: emptyList()
				userListLiveData.value = State(list, false)
			}
		}.execute()
	}

	private fun loadError() {
		object : AsyncTask<Void, Void, Throwable>() {
			override fun doInBackground(vararg params: Void?): Throwable {
				Thread.sleep(2000)
				return Throwable("Error loading")
			}

			override fun onPostExecute(result: Throwable) {
				errorLiveData.value = ViewModelError<Throwable>(result)
			}
		}.execute()
	}
}