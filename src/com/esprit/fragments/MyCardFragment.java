package com.esprit.fragments;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.esprit.utils.AndroidBarcodeView;

public class MyCardFragment extends Fragment {

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Intent i = getActivity().getIntent();
		AndroidBarcodeView view = new AndroidBarcodeView(getActivity(),i.getStringExtra("idClient"));
		return view;
	}
}
