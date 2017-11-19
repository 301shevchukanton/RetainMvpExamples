package com.example.loadermvp.mvp;

import com.example.loadermvp.loader.PresenterFactory;

/**
 * Created by AntonShevchuk on 18.11.2017.
 */

public class PresenterFactoryImpl implements PresenterFactory<UserList.Presenter> {
	@Override
	public UserList.Presenter create() {
		return new com.example.loadermvp.mvp.Presenter();
	}
}
