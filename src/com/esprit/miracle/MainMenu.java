package com.esprit.miracle;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Document;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.esprit.entities.Produit;
import com.esprit.fragments.AboutFragment;
import com.esprit.fragments.CartFragment;
import com.esprit.utils.DatabaseHelper;
import com.esprit.utils.GMapV2Direction;
import com.esprit.utils.GPSTracker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.ikimuhendis.ldrawer.ActionBarDrawerToggle;
import com.ikimuhendis.ldrawer.DrawerArrowDrawable;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;



@SuppressLint("ResourceAsColor")
public class MainMenu extends Activity {


    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerArrowDrawable drawerArrow;
    private boolean drawerArrowColor;
    
    //DB
	DatabaseHelper dbHelper;
	RuntimeExceptionDao<Produit, Integer> prodDao;
    //MAP
    private GoogleMap mMap;
    private MapFragment mMapFragment;
    private GPSTracker mGPS ;
    public GMapV2Direction md;
    private static boolean showMap=true;
    LatLng fromPosition =new LatLng(36.8339127,10.1789832);
	LatLng toPosition = new LatLng(36.8498327,10.2203717);
	
	//List Produit Cart
	List<Produit>list = null;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        ActionBar ab = getActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        ab.setHomeButtonEnabled(true);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.navdrawer);
        
        mMapFragment = ((MapFragment)getFragmentManager().findFragmentById(R.id.mapV2));
        mMap = mMapFragment.getMap();     
		mMapFragment.getView().setVisibility(View.INVISIBLE);
		MapDrawer(mMap);
		dbHelper=OpenHelperManager.getHelper(this, DatabaseHelper.class);
		prodDao=dbHelper.getProdRuntimeExceptionDao();
		
		drawerArrow = new DrawerArrowDrawable(this) {
            @Override
            public boolean isLayoutRtl() {
                return false;
            }
        };
        
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,
            drawerArrow, R.string.drawer_open,
            R.string.drawer_close) {

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                invalidateOptionsMenu();
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                invalidateOptionsMenu();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        
//        mDrawerList.addNavigationItem("Acceuil", R.drawable.prod, true);
//        mDrawerList.addNavigationItem("Catalogue", R.drawable.prod, false);
//        mDrawerList.addNavigationItem("Acceuil",R.drawable.prod , false);
//        mDrawerList.addNavigationItem("Acceuil", R.drawable.prod, false);
        
        String[] values = new String[]{
            "Acceuil",
            "Catalogue",
            "   Soins",
            "   Maquillage",
            "   Parfums Homme",
            "   Parfums Femme",
            "   Parfums Mixte",
            "   Coffrets pour Homme",
            "   Coffrets pour Femme",
            "About"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_1, android.R.id.text1, values);
        
        
        mDrawerList.setAdapter(adapter);
       mDrawerList.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			Intent intent;
       	 FragmentManager fm= getFragmentManager();
          Fragment fragment =null;
          switch (position) {
              case 0:
                    	//fm.beginTransaction().hide(FragmentMap).commit();
                  break;
              case 1:
              	Toast t=Toast.makeText(getApplicationContext(), "Veuillez Choisir une Categorie", 2000);
              	t.show();
                  break;
              case 2:
              	fragment = new Products(1);
                  break;
              case 3:
              	fragment = new Products(2);
                  break;
              case 4:
              	fragment = new Products(3);
                  break;
              case 5:
              	fragment = new Products(4);
                  break;
              case 6:
              	fragment = new Products(5);
                  break;
              case 7:
              	fragment = new Products(6);
                  break;
              case 8:
              	fragment = new Products(7);
                  break;
              case 9:
              	fragment = new Products(8);
                  break;
              case 10:
              	fragment = new AboutFragment();
                  break;
			
		}
        fm.beginTransaction()
        .replace(R.id.containerV2, fragment)
        .commit();
        mDrawerLayout.closeDrawer(mDrawerList);
        
        mMapFragment.getView().setVisibility(View.INVISIBLE);
       }
	});
    }

   
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	// TODO Auto-generated method stub
    	getMenuInflater().inflate(R.menu.magasins, menu);
    	return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            if (mDrawerLayout.isDrawerOpen(mDrawerList)) {
                mDrawerLayout.closeDrawer(mDrawerList);
            } else {
                mDrawerLayout.openDrawer(mDrawerList);
            }
        }
        
        if(item.getItemId()==R.id.action_map){
        	if(showMap){                         
        		mMapFragment.getView().setVisibility(View.VISIBLE);
        		showMap=false;
        	}else{
        		mMapFragment.getView().setVisibility(View.INVISIBLE);
        		showMap=true;
        	}
        	
        }
        if(item.getItemId()==R.id.action_shop){
        	FragmentManager fm= getFragmentManager();
    		list=prodDao.queryForAll();
            Fragment fragment =new CartFragment(list);
        	fm.beginTransaction()
            .replace(R.id.containerV2, fragment)
            .commit();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }
    
    
    public void MapDrawer(GoogleMap mMap){
    	mGPS=new GPSTracker(this);
    	
    	if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
    	 md = new GMapV2Direction();
 		
 		mMap.setMyLocationEnabled(true);

 		LatLng coordinates = new LatLng(36.8339127,10.1789832);		
 		mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(coordinates, 16));
 		
 		mMap.addMarker(new MarkerOptions().position(fromPosition).title("Vous ete ici !"));
 		mMap.addMarker(new MarkerOptions().position(toPosition).title("Magasin Miracle"));
 		
 		Document doc = md.getDocument(fromPosition, toPosition, GMapV2Direction.MODE_DRIVING);
 		int duration = md.getDurationValue(doc);
 		String distance = md.getDistanceText(doc);
 		
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
}