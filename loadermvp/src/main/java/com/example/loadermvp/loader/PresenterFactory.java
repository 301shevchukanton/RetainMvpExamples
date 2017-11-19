package com.example.loadermvp.loader;

public interface PresenterFactory<Presenter extends com.example.loadermvp.interfaces.Presenter> {
	Presenter create();
}