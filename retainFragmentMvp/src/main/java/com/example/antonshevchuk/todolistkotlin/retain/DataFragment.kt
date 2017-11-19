package com.example.antonshevchuk.todolistkotlin.retain

import android.app.Fragment
import android.os.Bundle

class DataFragment<Data> : Fragment() {

	var data: Data? = null

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		retainInstance = true
	}
}