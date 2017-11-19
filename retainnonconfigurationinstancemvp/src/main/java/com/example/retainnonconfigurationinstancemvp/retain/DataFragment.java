package com.example.retainnonconfigurationinstancemvp.retain;

import android.app.Fragment;
import android.os.Bundle;

public class DataFragment<Data> extends Fragment {

	private Data data;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Data getData() {
        return data;
    }
}