package com.elyes.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.esprit.miracle.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class Map_Fragment extends Fragment {
	GoogleMap googleMap;
	private static View rootView;
	double latitude=11.097409;
	double longitude=21.643066;
	String title;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView =inflater.inflate(R.layout.activity_main_menu, container,false);
		MakersInMap(rootView);
		return rootView;
	}
	
	public void MakersInMap(View v) {
		if (googleMap == null) {
			googleMap = ( (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map)).getMap();
		}
		if (googleMap != null) {
			googleMap.addMarker(new MarkerOptions().position(
					new LatLng(2020, 25.0)).title("test 7ob"));
		}
		MakersInMap( rootView,
	    		latitude,
	   		 longitude,
	   		 "aaaaaa"
	   		 );
	}

	 public void MakersInMap(View v, double lat, double longitude, String
			 title)
			 {
			 if(googleMap == null)
			 {
			 googleMap =
			 ((SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map)).getMap();
			 }
			 if(googleMap != null)
			 {
			 googleMap.addMarker(new MarkerOptions()
			 .position(new LatLng(lat,longitude))
			 .title(title)
			 );
			 }
			 }

}