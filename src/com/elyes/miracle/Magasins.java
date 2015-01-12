package com.elyes.miracle;

import java.util.ArrayList;

import org.w3c.dom.Document;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import com.elyes.utils.GMapV2Direction;
import com.elyes.utils.GPSTracker;
import com.elyes.miracle.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class Magasins extends Activity {
	public GoogleMap mMap;	
	public GMapV2Direction md;
	public GPSTracker mGPS ;
	LatLng fromPosition =new LatLng(36.8354531, 10.1463117);
	LatLng toPosition = new LatLng(36.8498327,10.2203717);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_magasins);
		
		
		mGPS=new GPSTracker(this);
		//fromPosition=new LatLng(mGPS.getLatitude(),mGPS.getLongitude());
		//mMap.moveCamera( CameraUpdateFactory.newLatLngZoom(new LatLng(36.899613, 10.189933) , 16.0f) );
		//fromPosition=new LatLng(mMap.getMyLocation().getLatitude(),mMap.getMyLocation().getLongitude());

//		String x=makeURL(36.8354531, 10.1463117, 36.8625, 10.195556);
//		JSONParser jParser = new JSONParser();
//		String json = jParser.getJSONFromUrl(x);
		
		if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
		com.gc.materialdesign.views.ButtonRectangle  tvDist=(com.gc.materialdesign.views.ButtonRectangle) findViewById(R.id.distance_m);
        
        md = new GMapV2Direction();
		mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
		mMap.setMyLocationEnabled(true);

		LatLng coordinates = new LatLng(36.8339127,10.1789832);		
		mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coordinates, 16));
		
		mMap.addMarker(new MarkerOptions().position(fromPosition).title("Vous ete ici !"));
		mMap.addMarker(new MarkerOptions().position(toPosition).title("Magasin Miracle"));
		
		Document doc = md.getDocument(fromPosition, toPosition, GMapV2Direction.MODE_DRIVING);
		int duration = md.getDurationValue(doc);
		String distance = md.getDistanceText(doc);
		tvDist.setText("Distance : "+distance);
		String start_address = md.getStartAddress(doc);
		String copy_right = md.getCopyRights(doc);
		
		
		Log.d("Durée",duration+"");
		Log.d("Distance :",distance+"");
		Log.d("start_address",start_address+"");
		Log.d("copy_right",copy_right+"");

		ArrayList<LatLng> directionPoint = md.getDirection(doc);
		PolylineOptions rectLine = new PolylineOptions().width(3).color(Color.RED);
		
		for(int i = 0 ; i < directionPoint.size() ; i++) {			
			rectLine.add(directionPoint.get(i));
		}
		
		mMap.addPolyline(rectLine);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.magasins, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	
}

