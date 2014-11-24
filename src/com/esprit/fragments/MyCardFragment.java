package com.esprit.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.esprit.utils.AndroidBarcodeView;

public class MyCardFragment extends Fragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		AndroidBarcodeView view = new AndroidBarcodeView(getActivity());
		return view;
	}
}
