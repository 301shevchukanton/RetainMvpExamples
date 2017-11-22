package com.example.architecturecomponents

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.os.AsyncTask
import com.example.architecturecomponents.recycler.UserItem
import java.io.Serializable

/**
 * Created by AntonShevchuk on 22.11.2017.
 */

class UserListViewModel : ViewModel() {

	class State(var userItems: List<UserItem> = emptyList(),
	            var isInProgress: Boolean = false,
	            var shouldUpdate: Boolean = true) : Serializable


	val userListLiveData = MutableLiveData<State>()

	init {
		this.userListLiveData.value = State(emptyList(), true, false)
	}

	fun loadList() {
		userListLiveData.value = State(emptyList(), true, false)
		object : AsyncTask<Void, Void, List<String>>() {
			override fun doInBackground(vararg params: Void?): List<String> {
				Thread.sleep(5000)
				return listOf("Peter", "Mike", "John Doe", "Clark Kent", "Bruce Wayne", "Peter Parker")
			}

			override fun onPostExecute(result: List<String>?) {
				val list = result?.map { UserItem(it) }?.toList() ?: emptyList()
				userListLiveData.value = State(list, false, false)
			}
		}.execute()
	}
}