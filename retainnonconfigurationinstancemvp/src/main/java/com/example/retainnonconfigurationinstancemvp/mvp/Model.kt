package com.example.retainnonconfigurationinstancemvp.mvp

import android.os.AsyncTask
import com.example.antonshevchuk.todolistkotlin.mvp.UserList
import com.example.antonshevchuk.todolistkotlin.recycler.UserItem

/**
 * Created by AntonShevchuk on 18.09.2016.
 */
public class Model(private var state: UserList.Model.State) : UserList.Model {
	private var listener: UserList.Model.Listener? = null

	override fun loadList() {
		state = UserList.Model.State(emptyList(), true)
		listener?.onUpdate(state)

		object : AsyncTask<Void, Void, List<String>>() {
			override fun doInBackground(vararg params: Void?): List<String> {
				Thread.sleep(5000)
				return listOf("Peter", "Mike", "John Doe", "Clark Kent", "Bruce Wayne", "Peter Parker")
			}

			override fun onPostExecute(result: List<String>?) {
				val list = result?.map { UserItem(it) }?.toList() ?: emptyList()
				state = UserList.Model.State(list, false)
				listener?.onUpdate(state)
			}
		}.execute()
	}

	override fun setListener(listener: UserList.Model.Listener?) {
		this.listener = listener
		this.listener?.onUpdate(this.state)
	}

}